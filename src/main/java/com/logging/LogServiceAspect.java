package com.logging;

import com.model.LogModel;
import com.repository.ILogRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
public class LogServiceAspect
{
    @Autowired
    private ILogRepository logRepository;

    @Around("execution(* com.controller..*(..))")
    public Object createLog(ProceedingJoinPoint joinPoint) throws Throwable
    {
        LogModel logModel = new LogModel();
        logModel.setInput(joinPoint.getArgs());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logModel.setMethodName(method.getName());
        logModel.setClassName(method.getDeclaringClass().getName());
        Date date = new Date(System.currentTimeMillis());
        logModel.setMoment(date);
        logRepository.save(logModel);

        return joinPoint.proceed();
    }
}
