package be.vdab.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by maarten on 12/03/2017.
 */
@Aspect
class PointcutExpressions {

    @Pointcut("execution(* be.vdab.services.*.*(..))")
    void services(){}

    @Pointcut("execution(* be.vdab.services.*.*(..)) || execution(* org.springframework.transaction.*.*(..))")
    void servicesEnTransacties(){}
}
