package com.nm.cost.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 韩老魔
 * 全局捕获异常：异常通知类AOP
 * 坑了一下午该异常类进不了原因：
 * 没加 @ResponseBody或者没设置out.write();
 * 该类类为ajax响应而不能返回string通过视图解析器跳转到error页面
 * @Date: 2019/4/7 0007 15:11
 */
@ControllerAdvice
@Slf4j
public class CustomException {

    //拦截所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String, String> myCustomException(Exception e ) {
        Map<String, String> map = new HashMap<>();
        if (e != null) {
//            自定义异常
//            手动抛出Myexception后会被该hanlder拦截
            if (e instanceof MyException) {
                map.put("code", ((MyException) e).getCode());
                map.put("message", e.getMessage());
                log.warn("你猜1"+e.getMessage());
                return map;
            } else {
                //非自定义全局捕获
                map.put("code", "401");
                map.put("message", "全局捕获异常");
                log.warn("你猜1"+e.getMessage());
                return map;
            }
        }
        return null;

    }

}
