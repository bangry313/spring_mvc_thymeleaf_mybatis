package com.ezen.springmvc.web.member.controller;

import com.ezen.springmvc.web.common.rest.ErrorResult;
import com.ezen.springmvc.web.common.rest.Result;
import com.ezen.springmvc.web.member.form.MemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@Slf4j
@RequiredArgsConstructor
public class MemberRestController {
    private final MessageSource messageSource;

    // 회원 가입 요청 처리
//    @PostMapping("/signup")
//    public Object signUpAction(@RequestBody @Validated MemberForm memberForm, BindingResult bindingResult) {
//        log.info("회원 정보 : {}", memberForm.toString());
//        if(bindingResult.hasErrors()){
//            // bindingResult에 저장된 검증 오류 메시지들을 응답 메시지 본문에 JSON으로 출력
//            return bindingResult.getAllErrors();
//        }
//        return memberForm;
//     }

    // 회원 가입 요청 처리
    @PostMapping("/signup")
    public ResponseEntity signUpAction(@RequestBody @Validated MemberForm memberForm, BindingResult bindingResult) {
        log.info("회원 정보 : {}", memberForm.toString());
        if(bindingResult.hasErrors()){
            ErrorResult errorResults = new ErrorResult(bindingResult);
            return  ResponseEntity.badRequest().body(errorResults);
        }
        Result result = Result.builder()
                .status(HttpStatus.OK)
                .message("정상 처리되었습니다.")
                .data(memberForm)
                .build();

        return ResponseEntity.ok().body(result);
    }
}





