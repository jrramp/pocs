package com.ramp.poc.hibernate.validate.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.*;
import java.util.Set;

/**
 * This is used to com.ramp.poc.hibernate.validate the response received through rest template in task service, it helps to keep logging
 * statements separate
 * from business logic.
 */
@Aspect
@Component
public class ValidationAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);


    /**
     * Around advice to log public methods input params, returned result and execution time.
     *
     * @param jp JoinPoint
     * @return result of actual method execution
     * @throws Throwable exception thrown by actual method
     */
    @AfterReturning(pointcut = "execution(* org.springframework.web.client.RestTemplate*.*(..))", returning = "result")
    public void profile(JoinPoint jp, Object result) throws Throwable {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(result);
        if (constraintViolations.size() > 0) {
            throw new ValidationException("Not valid object" + constraintViolations);
        }
    }

}
