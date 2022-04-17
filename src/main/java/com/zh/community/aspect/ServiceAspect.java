package com.zh.community.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author :珠代
 * @description :
 * @create :2022-04-09 20:20:00
 */
@Component
@Aspect
public class ServiceAspect {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut("execution(* com.zh.community.service.*.*(..))")
    public void pointcut(){
    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        //用户[1,2,3,4]在[xxx]访问了[com.zh.community.service.xxx()]
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null){
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteHost();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String target = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        logger.info(String.format("用户[%s],在[%s]访问了[%s]",ip,now,target));
    }

}
