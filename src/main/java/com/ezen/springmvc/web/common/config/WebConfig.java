package com.ezen.springmvc.web.common.config;

import java.util.Arrays;
import java.util.List;

import com.ezen.springmvc.web.common.interceptor.LoggerInterceptor;
import com.ezen.springmvc.web.common.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** 인터셉터 등록을 위한 자바 설정 클래스 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 로그인 체크가 필요 없는 요청 URI 목록
    public static final  List<String> loginNotEssential =
            Arrays.asList(
                    "/", "/css/**", "/img/**", "/js/**", "/*.ico", "/vendor/**",
                    "/member/signup/**", "/member/idcheck/{inputId}", "/member/image/{profileFileName}",
                    "/member/signin", "/member/signout", "/api/member/signup");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 로그 기록 인터셉터 등록
        registry.addInterceptor(new LoggerInterceptor())
                .order(1)
                // 요청 URI에 대한 로그 기록 인터셉터 매핑 (하위 패스에 상관없이 모든 요청에 매핑)
                .addPathPatterns("/**") // 비정형 매개변수이기때문에 여러개 등록 가능
                //인터셉터에서 제외할 패턴 설정
                .excludePathPatterns("/*.ico", "/*.html", "/css/**", "/vendor/**", "/error");
        // 로그인 체크 인터셉터 등록
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(loginNotEssential);
    }
}





