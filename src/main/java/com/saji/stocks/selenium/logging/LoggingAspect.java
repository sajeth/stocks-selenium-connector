//package com.saji.stocks.selenium.logging;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Aspect
//@Component
//public class LoggingAspect {
//    private final Logger log = Logger.getLogger(this.getClass().getName());
//
//
//    @Pointcut("execution(private * *(..))")
//    protected void loggingOperation() {
//    }
//
//    @Before("execution(* *.*(..))")
//    @Order(1)
//    public void logJoinPoint(JoinPoint joinPoint) {
//        log.info("The method " + joinPoint.getSignature().getName() + "() begins with " + Arrays.toString(joinPoint.getArgs()));
//    }
//
////    @AfterReturning(pointcut = "loggingOperation()", returning = "result")
////    @Order(2)
////    public void logAfter(JoinPoint joinPoint, Object result) {
////        log.info("Exiting from Method :" + joinPoint.getSignature().getName());
////        log.info("Return value :" + result);
////    }
//
//    @AfterThrowing(pointcut = "execution(* *.*(..))", throwing = "e")
//    @Order(3)
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        log.log(Level.SEVERE, "An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
//        log.log(Level.SEVERE, "Cause :" + e.getCause());
//    }
//
////    @Around("execution(* *.*(..))")
////    @Order(4)
////    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
////        log.info("The method " + joinPoint.getSignature().getName() + "() begins with " + Arrays.toString(joinPoint.getArgs()));
////        try {
////            Object result = joinPoint.proceed();
////            log.info("The method " + joinPoint.getSignature().getName() + "() ends with " + result);
////            return result;
////        } catch (IllegalArgumentException e) {
////            log.log(Level.SEVERE, "Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in " + joinPoint.getSignature().getName() + "()");
////            throw e;
////        }
////    }
//
//}
