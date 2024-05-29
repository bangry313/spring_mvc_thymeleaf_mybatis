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
/** 게시판 DTO */
public class BoardDTO {
	int boardId;			/** 게시판 식별번호 */
	int category;			/** 게시판 카테고리 */
	String title;			/** 게시판 이름 */
	String description;		/** 게시판 상세설명 */
}