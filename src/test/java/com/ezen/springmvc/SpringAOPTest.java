package com.ezen.springmvc;

import com.ezen.springmvc.domain.member.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ezen.springmvc.domain.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@SpringBootTest
@Slf4j
public class SpringAOPTest {
	
	@Autowired
	private MemberService memberService;
	
	@Test
	public void getMemberTest() {
		log.info("조회된 memberService : {}", memberService);
		List<MemberDto> list  = memberService.getMembers();
		log.info("회원 전체 목록 : {}", list);
	}
	
}
