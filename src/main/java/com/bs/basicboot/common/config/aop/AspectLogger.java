package com.bs.basicboot.common.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j

public class AspectLogger {

    @Before("execution(* com.bs.basicboot..*(..))")
    public void beforeLog(JoinPoint jp) {
        log.warn("======before log aop======");
        Signature sig = jp.getSignature();
        log.warn(sig.getDeclaringTypeName() + "." + sig.getName());
        log.warn("=======================");


    }
}
