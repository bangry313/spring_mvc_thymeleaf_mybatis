package com.ezen.springmvc.domain.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogAspect {
	
	@Before("execution(* com.ezen.springmvc.web..*(..))")
	public void beforeLog() {
		log.info("------------- 세부 컨트롤러 실행 호출됨 -------------");
	}
	
	@After("execution(* com.ezen.springmvc.web..*(..))")
	public void afterLog() {
		log.info("------------- 세부 컨트롤러 실행 종료 -------------");
	}
	
	@AfterReturning(pointcut = "execution(* com.ezen.springmvc.web..*(..))", returning="returnValue")
	public void afterReturnLog(Object returnValue) {
		log.info("- 세부 컨트롤러 실행 후 반환값 : " + returnValue);
	}
	
	@Around("execution(* com.ezen.springmvc.web..*(..))")
	public Object aroundLog(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().toShortString();
		Object[] arsg = joinPoint.getArgs();
		log.info("- 세부 컨트롤러 이름 : " + className);
		log.info("- 세부 메소드 이름 : " + methodName);
		log.info("- 전달 인자들");
		for (Object object : arsg) {
			log.info("   . " + object.toString());
		}
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
}











