package com.ezen.springmvc;

import java.util.Locale;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MessageSourceTest {
	
	@Autowired
	private MessageSource messageSource;
	
	@Test
	void messageTest() {
		String title = messageSource.getMessage("memberForm.title.register",null, null);
		String id = messageSource.getMessage("memberForm.label.id",null, null);
		log.info("등록 화면 타이틀 : {}", title);
		log.info("아이디 라벨 : {}", id);
	}

	@Test
	void messageDefaultTest() {
		// 디폴트 메시지 설정
		String message = messageSource.getMessage("no_key", null, "반값습니다. 손님!!", null);
		log.info("메시지 : {}", message);
	}

	@Test
	void messageParamTest() {
		// 매개변수 사용
		String[] params = {"김기정"};
		String message = messageSource.getMessage("hello.name", params, null);
		log.info("메시지 : {}", message);
	}
	
	@Test
	@DisplayName("국제화 처리 테스트")
	@Disabled
	void messageInternationalTest() {
		String title = messageSource.getMessage("memberForm.title.register",null, Locale.ENGLISH);
		log.info("타이틀 : {}", title);
		String[] params = {"김기정"};
		String message = messageSource.getMessage("hello.name", params, null, Locale.ENGLISH);
		log.info("메시지 : {}", message);
	}
	
}

