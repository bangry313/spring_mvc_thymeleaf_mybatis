package com.ezen.springmvc.domain.board.service;

import java.util.List;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.common.dto.SearchDto;

/** 게시판 관련 비즈니스 로직 처리 및 트랜잭션 관리 */
public interface BoardService {
	
	/** 신규 게시판 생성 */
	public void createBoard(BoardDTO boardDTO);
	
	/** 전체 게시판 목록 반환 */
	public List<BoardDTO> getBoardList();
	
	/** 게시판 번호로 게시판 상세 조회 */
	public BoardDTO getBoard(int boardId);

	/** 신규 게시글 등록 */
	public void writeArticle(ArticleDTO articleDTO);

	/** 특정 게시판의 검색 조건에 따른 게시글 목록 반환 */
	public List<ArticleDTO> getArticleList(int boardId, SearchDto searchDto);

	/** 특정 게시판의 검색 조건에 따른 게시글 수 반환 */
	public int getArticleCount(int boardId, SearchDto searchDto);
}