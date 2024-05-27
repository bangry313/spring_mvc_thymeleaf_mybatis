package com.ezen.springmvc.web.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/** 로그인 체크 인터셉터 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		// 로그인 하지 않은 상태의 요청인 경우
		if (session == null ||  session.getAttribute("loginMember") == null) {
			log.info(">>> 로그인 하지 않은 사용자 요청!!!");
			// 로그인 화면으로 리다이렉트
			// 만약 쿼리 쿼리스트링이 존재하는 경우
			String queryString = request.getQueryString();
			String redirectURI = queryString == null ? requestURI : requestURI + "?" + queryString;
			//세션에 redirectURI 설정
			session.setAttribute("redirectURI", redirectURI);
			// 로그인 화면으로 리다이렉트
			response.sendRedirect("/member/signin?redirectURI=" + requestURI);
			return false;
		}
		return true;
	}
}

