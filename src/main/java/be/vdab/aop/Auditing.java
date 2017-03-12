package be.vdab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by maarten on 12/03/2017.
 */
@Aspect
@Component
@Order(1)
class Auditing {
    private final static Logger LOGGER = Logger.getLogger(Auditing.class.getName());

    @AfterReturning(pointcut = "be.vdab.aop.PointcutExpressions.services()", returning = "returnValue")
    void schrijfAudit(JoinPoint joinpoint, Object returnValue) {
        StringBuilder builder = new StringBuilder("\nTijdstip\t").append(LocalDateTime.now());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !"anonymousUser".equals(authentication.getName())) {
            builder.append("\nGebruiker\t").append(authentication.getName());
        }
        builder.append("\nMethod\t\t").append(joinpoint.getSignature().toLongString());
        Arrays.stream(joinpoint.getArgs())
                .forEach(o -> builder.append("\nParameter\t").append(o));
        if (returnValue != null) {
            builder.append("\nReturn\t\t");
            if (returnValue instanceof Collection) {
                builder.append(((Collection<?>) returnValue).size()).append(" objects");
            } else {
                builder.append(returnValue.toString());
            }
        }
        LOGGER.info(builder.toString());
    }
}
