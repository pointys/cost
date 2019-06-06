package com.nm.cost.interceptor;

import com.nm.cost.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: 韩老魔
 * 实现拦截器接口
 * @Date: 2019/2/27 0027 12:05
 */

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static int count = 0;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //返回true允许请求 false拦截
        String requestUri = request.getRequestURI();
        System.out.println("request:" + requestUri);
        User userInfo = (User) request.getSession().getAttribute("userInfo");
        if (userInfo == null) {
            log.info("拦截：" + count++ + "次");
            response.sendRedirect(request.getContextPath() + "/system/login");
            return false;
        } else {
            return true;
        }
    }

    /**
     * -
     * 生成视图之前执行，可以修改ModelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 生成视图时执行，可以用来处理异常，并记录在日志中
     *
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
