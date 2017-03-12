package be.vdab.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Created by maarten on 12/03/2017.
 */
@Aspect
@Component
public class Statistieken {
    private static final Logger LOGGER = Logger.getLogger(Statistieken.class.getName());
    private final ConcurrentHashMap<String, AtomicInteger> statistieken = new ConcurrentHashMap<>();

    @After("be.vdab.aop.PointcutExpressions.services()")
    void statistiekBijwerken(JoinPoint joinPoint) {
        String joinPointSignatuur = joinPoint.getSignature().toLongString();
        statistieken.computeIfPresent(joinPointSignatuur, (k,v) -> new AtomicInteger(v.incrementAndGet()));
        statistieken.putIfAbsent(joinPointSignatuur, new AtomicInteger(1));
        int aantal = statistieken.get(joinPointSignatuur).get();
        LOGGER.info(joinPointSignatuur + " werd " + aantal + " keer opgeroepen");
    }
}
