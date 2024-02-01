package com.practice.aop.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogsServicesRest {
    final static Logger logger = LoggerFactory.getLogger(LogsServicesRest.class);

    @Before("execution(* com.practice.aop.aop.rest.*.*(..))")
    public void beforeGetPersonById(JoinPoint joinPoint) {
        String argument = joinPoint.getArgs().length != 0? joinPoint.getArgs()[0].toString():"No tiene argumentos";

        logger.info("Se ingreso al metodo: {} \n con los parametros: {}", joinPoint.getSignature().getName(),
                argument);
    }

    @AfterReturning(pointcut = "execution(* com.practice.aop.aop.rest.*.*(..))", returning = "entity")
    public void logReturning(JoinPoint joinPoint, ResponseEntity<?> entity) {
        logger.info("Saliendo del metodo: {} \n con el estatus y info: {} {}", joinPoint.getSignature().getName(),
                entity.getStatusCode(), entity.getBody());
    }
}
