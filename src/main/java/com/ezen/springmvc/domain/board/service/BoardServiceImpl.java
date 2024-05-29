package com.ezen.springmvc.domain.board.service;

import java.util.List;

import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.mapper.ArticleMapper;
import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.springframework.stereotype.Service;

import com.ezen.springmvc.domain.board.dto.BoardDTO;
import com.ezen.springmvc.domain.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * BoardService 구현체 */
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	private final ArticleMapper articleMapper;

	@Override
	@Transactional
	public void createBoard(BoardDTO boardDTO) {
		boardMapper.create(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList() {
		return boardMapper.findByAll();
	}

	@Override
	public BoardDTO getBoard(int boardId) {
		return boardMapper.findById(boardId);
	}

	@Override
	@Transactional
	public void writeArticle(ArticleDTO articleDTO) {
		articleMapper.create(articleDTO);
	}

	@Override
	public List<ArticleDTO> getArticleList(int boardId, SearchDto searchDto) {
		return articleMapper.findBySearchCondition(boardId, searchDto);
	}

	@Override
	public int getArticleCount(int boardId, SearchDto searchDto) {
		return articleMapper.countBySearchCondition(boardId, searchDto);
	}
}