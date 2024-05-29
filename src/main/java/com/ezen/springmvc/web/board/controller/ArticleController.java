package com.ezen.springmvc.web.board.controller;

import com.ezen.springmvc.domain.common.dto.SearchDto;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.web.board.form.ArticleForm;
import com.ezen.springmvc.web.common.page.Pagination;
import com.ezen.springmvc.web.common.page.ParameterForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.ezen.springmvc.domain.board.dto.ArticleDTO;
import com.ezen.springmvc.domain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * 게시글 관련 요청 세부 컨트롤러
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class ArticleController {

    private final BoardService boardService;

    // 게시글 쓰기 화면 요청 처리
    @GetMapping("/{boardId}/article/write")
    public String articleWriteForm(Model model, HttpServletRequest request) {
        ArticleForm articleForm = ArticleForm.builder().build();
        HttpSession session = request.getSession(false);
        if(session != null){
            MemberDto memberDto = (MemberDto) session.getAttribute("loginMember");
            if(memberDto != null){
                articleForm.setWriter(memberDto.getId());
            }
        }

        model.addAttribute("articleForm", articleForm);
        return "/board/articleWriteForm";
    }

    // 게시글 쓰기 요청 처리 (Bean Validation 사용)
    @PostMapping("/{boardId}/article/write")
    public String articleWriteAction(@PathVariable("boardId") int boardId, @Validated @ModelAttribute ArticleForm articleForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("게시글 정보 : {}", articleForm.toString());
        if (bindingResult.hasErrors()) {
            return "/board/articleWriteForm";
        }
        ArticleDTO articleDTO = ArticleDTO.builder()
                .boardId(boardId)
                .title(articleForm.getTitle())
                .content(articleForm.getContent())
                .writer(articleForm.getWriter())
                .passwd(articleForm.getPasswd())
                .build();
        boardService.writeArticle(articleDTO);
//		redirectAttributes.addFlashAttribute("memberDto", memberDto);
        return "redirect:/";
    }

    // 특정 게시판의 게시글 목록 요청 처리(검색 + 페이징 처리)
    @GetMapping("/{boardId}/article")
    public String articleListAction(@PathVariable("boardId") int boardId, @ModelAttribute ParameterForm parameterForm, Model model) {
        log.info("요청 게시판 번호 : {}", boardId);
        log.info("요청 파라메터 정보 : {}", parameterForm);

        SearchDto searchDto = SearchDto.builder()
                .limit(parameterForm.getElementSize())
                .page(parameterForm.getRequestPage())
                .searchValue(parameterForm.getSearchValue())
                .build();
        
        // 게시글 목록 조회
        List<ArticleDTO> articleList = boardService.getArticleList(boardId, searchDto);
        
        // 페이징 처리를 위한 테이블 행의 개수 조회
        int selectedRowCount = boardService.getArticleCount(boardId, searchDto);
        parameterForm.setRowCount(selectedRowCount);

        Pagination pagination = new Pagination(parameterForm);

        model.addAttribute("articleList", articleList);
        model.addAttribute("parameterForm", parameterForm);
        model.addAttribute("pagination", pagination);
        return "/board/articleList";
    }
}