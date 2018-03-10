package com.kodilla.patterns2.facade;

import com.kodilla.patterns2.facade.api.OrderDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FacadeWatcher {
    public static final Logger LOGGER = LoggerFactory.getLogger(FacadeWatcher.class);

    @Before("execution(* com.kodilla.patterns2.facade.api.OrderFacade.processOrder(..))" +
            " && args(order, userId) && target(object)")
    public void logEvent(OrderDto order, Long userId, Object object) {
        LOGGER.info("Executing " + object.getClass().getSimpleName() + ".processOrder(OrderDto, userId) for " +
                order.getItems().size() + " ordered item(s) and userId = " + userId
        );
    }

    @Around("execution(* com.kodilla.patterns2.facade.api.OrderFacade.processOrder(..))")
    public Object measureTime(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        long begin = System.currentTimeMillis();
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage());
            throw throwable;
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("Time consumed: " + (end - begin) + "[ms]");
        }
        return result;
    }
}
