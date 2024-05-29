package com.ezen.springmvc.domain.board.mapper;

import com.ezen.springmvc.domain.common.dto.SearchDto;
import org.apache.ibatis.annotations.Mapper;
import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * article 테이블 관련 Mapper
 */
@Mapper
public interface ArticleMapper {

    /**
     * 신규 게시글 등록
     */
    public void create(ArticleDTO articleDTO);

    /**
     * 특정 게시판의 검색 조건에 따른 게시글 전체 목록 반환
     */
    public List<ArticleDTO> findBySearchCondition(@Param("boardId") int boardId, @Param("searchDto") SearchDto searchDto);

    /**
     * 특정 게시판의 검색 조건에 따른 게시글 전체 갯수 반환
     */
    public int countBySearchCondition(@Param("boardId") int boardId, @Param("searchDto") SearchDto searchDto);

}