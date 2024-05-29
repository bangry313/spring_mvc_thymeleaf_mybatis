package com.ezen.springmvc;


import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;

	@Test
	@DisplayName("신규 게시판 생성")
	@Disabled
	public void createTest() {
		BoardDTO boardDTO = BoardDTO.builder()
				.category(2)
				.title("리뷰게시판")
				.description("리뷰를 달 수 있는 게시판입니다.")
				.build();
		boardMapper.create(boardDTO);
		log.info("신규 게시판 생성 완료 : {}", boardDTO);
	}

	@Test
	@DisplayName("전체 게시판 조회")
	@Disabled
	public void findByAllTest() {
		List<BoardDTO> boardList = boardMapper.findByAll();
		log.info("전체 게시판 목록 : {}", boardList);
	}

	@Test
	@DisplayName("게시판 상세 조회")
//	@Disabled
	public void findByIdTest() {
		int boardId = 10;
		BoardDTO boardDTO = boardMapper.findById(boardId);
		log.info("게시판 상세 : {}", boardDTO);
	}
}