package com.ezen.springmvc.web.common.interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/** 웹 클라이언트 요청에 대한 로그 기록 인터셉터 */
@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

	// 세부 컨트롤러 실행 전
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 요청 URI(패스)
		String requestURI = request.getRequestURI();
		log.info("웹 클라이언트 요청 URI = {}, 요청 처리 세부컨트롤러 = {}", requestURI, handler);
		return true; // false 진행 X
	}

	// 세부 컨트롤러 실행 후
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {
		log.info("{} 세부 컨트롤러 실행 후 반환값 = {}", handler, modelAndView);
	}

	// 뷰 렌더링 후
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
								Exception exception)	throws Exception {
		String requestURI = request.getRequestURI();
		log.info("{} 요청에 대한 응답 처리 완료", requestURI);
		if (exception != null) {
			log.error("세부 컨트롤러 실행 중 오류 발생 : {}", exception);
		}
	}
}

