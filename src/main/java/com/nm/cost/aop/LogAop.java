package com.nm.cost.aop;

import com.nm.cost.utils.Ipv4AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.SocketException;

/**
 * @Author: 韩老魔
 * @Date: 2019/5/11 0011 18:58
 */
@Aspect
@Component
@Slf4j
public class LogAop {
    /*
     * 定义一个切入点
     */
    // @Pointcut("execution (* findById*(..))")
    @Pointcut("execution(public * com.nm.cost.controller.system.*.*(..))")

    /*切点签名*/
    public void print() {

    }
    /*@Before注解表示在具体的方法之前执行*/
    @Before("print()")
    public void before(JoinPoint joinPoint) {
        System.out.println("===============进入切点");
        log.info("前置切面before……");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        String remoteAddr = null;
        try {
            remoteAddr = Ipv4AddressUtils.getLocalIpv4Address();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        String requestMethod = request.getMethod();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("请求url=" + requestURI + ",客户端ipv4=" + remoteAddr + ",请求方式=" + requestMethod + ",请求的类名=" + declaringTypeName + ",方法名=" + methodName + ",入参=" + args);
    }

}
