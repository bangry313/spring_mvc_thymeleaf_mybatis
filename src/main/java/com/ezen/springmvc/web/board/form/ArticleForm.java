package com.ezen.springmvc.web.board.form;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/** 신규 게시글 쓰기 폼에 대응하는 Form 클래스 */
public class ArticleForm {
    @NotBlank(message = "제목을 입력하여 주세요.")
    private String title;
    @NotBlank(message = "내용을 입력하여 주세요.")
    private String content;
    @NotBlank(message = "비번을 입력하여 주세요.")
    private String passwd;
    private String writer;
}

