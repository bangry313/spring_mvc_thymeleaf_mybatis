package com.ezen.springmvc.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
/** 게시글 DTO */
public class ArticleDTO {
	int articleId;		/** 게시글 식별번호 */
	int boardId;		/** 게시글 소속 게시판번호 */
	String writer;		/** 게시글 작성자 아이디 */
	String title;		/** 게시글 제목 */
	String content;		/** 게시글 내용 */
	String regdate;		/** 게시글 등록일자 */
	int hitCount;		/** 게시글 조회수 */
	String passwd;		/** 게시글 비밀번호 */
	int groupNo;		/** 계층형 게시판 구조를 위한 게시글 그룹번호 */
	int levelNo;		/** 계층형 게시판 구조를 위한 그룹내 게시글 레벨 */
	int orderNo;		/** 계층형 게시판 구조를 위한 그룹내 게시글 순서 */
}