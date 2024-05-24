package com.ezen.springmvc.domain.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

//@Component
//@Aspect
@Slf4j
public class TimeTraceAspect {

	// 실행 시간 측정 및 로그 기록 공통기능 정의(포인트컷 표현식을 이용한 적용 시점 설정)
	@Around("execution(* com.ezen.springmvc..*(..))")
	public Object timeCheck(ProceedingJoinPoint joinPoint) {
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().toShortString();
		long start = System.currentTimeMillis();
		log.info("========== {} 객체의 {} 메서드 실행 ==========", className, methodName);
		Object object = null;
		try {
			object = joinPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}finally {
			long end = System.currentTimeMillis();
			long time = end - start;
			log.info("- {}  메서드 실행 시간 : {}", methodName, time);
			log.info("========== {} 객체의 {} 메서드 종료 ==========", className, methodName);
		}
		return object;
	}
}











