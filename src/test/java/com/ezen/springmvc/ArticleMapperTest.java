package com.ezen.springmvc;


import java.util.List;

import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Test
    @DisplayName("게시글 등록")
	@Disabled
    public void createTest() {
        ArticleDTO newArticle = ArticleDTO.builder()
                .boardId(10)
                .writer("monday")
                .title("월요일 신규글 등록 테스트입니다.")
                .content("월요일 신규글 내용입니다.")
                .passwd("1111")
                .levelNo(0)
                .orderNo(0)
                .build();
        articleMapper.create(newArticle);
        log.info("신규 게시글 등록 완료 : {}", newArticle);
    }

    @Test
    @DisplayName("특정 게시판의 전체 게시글 조회")
    @Disabled
    public void findByAllTest() {
        int boardId = 10;
        SearchDto searchDto = SearchDto.builder()
                .limit(10)
                .page(1)
                .searchValue("댓글")
                .build();
        List<ArticleDTO> allList = articleMapper.findBySearchCondition(boardId, searchDto);
        log.info("===== 전체 게시글 목록 =====");
        allList.forEach(articleDTO -> log.info("게시글: {}", articleDTO));
    }

    @Test
    @DisplayName("특정 게시판의 전체 게시글수 조회")
    @Disabled
    public void countBySearchConditionTest() {
        int boardId = 10;
        SearchDto searchDto = SearchDto.builder()
                .limit(10)
                .page(1)
                .searchValue("monday")
                .build();
        int count = articleMapper.countBySearchCondition(boardId, searchDto);
        log.info("===== 전체 게시글 목록 =====");
        log.info("검색 게시글 수 : {}", count);
    }

//	@Test
//	@DisplayName("게시글 찾기")
//	@Disabled
//	public void findTest() {
//		ArticleDTO articleDTO = articleMapper.find(1);
//		log.info("게시글 찾기 완료 : {}", articleDTO);
//	}
//
//	@Test
//	@DisplayName("페이징 계산(검새값 포함)에 필요한 게시글 전체 갯수")
//	@Disabled
//	public void getCountAllTest() {
//		int boardId = 10;
//		String keyword = "제목";
//		int count = articleMapper.getCountAll(boardId, keyword);
//		log.info("게시글 전체 갯수 : {}", count);
//	}
//
//	@Test
//	@DisplayName("페이지당 보여지는 목록 갯수에 따른 게시글 목록")
//	@Disabled
//	public void findByAllTest() {
//		// 페이징 계산(검새값 포함)에 필요한 게시글 전체 갯수
//		int boardId = 10;
//		String keyword = null;
//		int count = articleMapper.getCountAll(boardId, keyword);
//
//		PageParams pageParams = PageParams.builder()
//										  .elementSize(10)
//										  .pageSize(10)
//										  .requestPage(1)
//										  .rowCount(count)
//										  .boardId(boardId)
//										  .keyword(keyword)
//										  .build();
//		List<ArticleDTO> pagingArticleList = articleMapper.findByAll(pageParams);
//		log.info("페이징된 게시글 리스트 : {}", pagingArticleList);
//	}
//
//	@Test
//	@DisplayName("댓글, 대댓글 등록 기능을 위한 부모 게시글 검색")
//	@Disabled
//	public void findArticleTest() {
//		ArticleDTO articleDTO = articleMapper.findArticle(1);
//		log.info("부모 게시글 : {}", articleDTO);
//	}
//
//	@Test
//	@DisplayName("댓글 등록")
//	@Disabled
//	public void createReplyTest() {
//		// 댓글 등록 할 게시글 검색
//		ArticleDTO topArticle = articleMapper.findArticle(1);
//		// 댓글 등록 전 orderNo 업데이트
//		articleMapper.updateOrderNo(topArticle);
//		// 댓글 생성
//		ArticleDTO newReply = ArticleDTO.builder()
//										.boardId(10)
//										.writer("monday")
//										.subject("댓글 등록 테스트 1")
//										.content("댓글 등록 테스트 내용")
//										.passwd("1111")
//										.groupNo(topArticle.getGroupNo())
//										.levelNo(topArticle.getLevelNo())
//										.build();
//		articleMapper.createReply(newReply, topArticle);
//		log.info("댓글 등록 완료 : {}", topArticle);
//	}
//
//
//	@Test
//	@DisplayName("게시글 상세보기")
//	@Disabled
//	public void readArticleTest() {
//		int articleId = 1;
//		// 게시글 상세보기 시 조회수 갱신
//		articleMapper.updateHitCount(articleId);
//		// 게시글 상세보기
//		ArticleDTO readArticle= articleMapper.readArticle(articleId);
//		log.info("게시글 상세보기 : {}", readArticle);
//	}
//
//	@Test
//	@DisplayName("게시글 업데이트")
//	@Disabled
//	public void updateTest() {
//		// 업데이트 게시물 생성
//		ArticleDTO updateArticle = ArticleDTO.builder()
//											 .articleId(1)
////											 .boardId(20)
//											 .subject("제목 수정 1")
////											 .content("내용 수정 1")
//											 .build();
//		articleMapper.update(updateArticle);
//		log.info("게시글 수정 완료 : {}", updateArticle);
//	}
//
//	@Test
//	@DisplayName("게시글 삭제")
//	@Transactional
//	@Disabled
//	public void deleteTest() {
//		int deleteArticleId = 13;
//		// 삭제할 게시글 검색
//		ArticleDTO deleteArticle = articleMapper.find(deleteArticleId);
//		articleMapper.delete(deleteArticle);
//		log.info("게시글 삭제 완료 : {}");
//	}
}