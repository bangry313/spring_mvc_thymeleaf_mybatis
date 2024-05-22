package com.ezen.springmvc.web.member.controller;

import com.ezen.springmvc.domain.common.dto.UploadFile;
import com.ezen.springmvc.domain.common.service.FileService;
import com.ezen.springmvc.domain.member.dto.MemberDto;
import com.ezen.springmvc.domain.member.service.MemberService;
import com.ezen.springmvc.web.member.form.LoginForm;
import com.ezen.springmvc.web.member.form.MemberForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberControllerV3 {

    @Value("${upload.profile.path}")
    private String profileFileUploadPath;

    private final FileService fileService;
    private final MemberService memberService;

    // 회원 가입 화면 요청 처리
    @GetMapping("/signup")
    public String signUpForm(Model model) {
        MemberForm memberForm = MemberForm.builder().build();
        model.addAttribute("memberForm", memberForm);
        return "/member/signUpFormV3";
    }

    // 회원 가입 요청 처리 (BindingResult 활용한 데이터 유효성 검증 처리 & 오류 메시지 통합 관리)
    @PostMapping("/signup")
    public String signUpAction(@ModelAttribute MemberForm memberForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("회원 정보 : {}", memberForm.toString());

        // 입력 폼 필드에 대한 데이터 검증 (StringUtils 클래스 활용)
        if(!StringUtils.hasText(memberForm.getId())){
            // new FieldError("검증 폼객체", "검증 실패 필드명", "검증 오류 메시지");
            FieldError fieldError = new FieldError("memberForm", "id", memberForm.getId(), false, new String[]{"required.memberForm.id"}, null, null);
            bindingResult.addError(fieldError);
        }else{
            if(memberForm.getId().length() < 6 || memberForm.getId().length() > 10){
                bindingResult.addError(new FieldError("memberForm", "id", memberForm.getId(), false,  new String[]{"range.memberForm.id"}, new Object[]{6, 10}, null));
            }
        }
        if(!StringUtils.hasText(memberForm.getName())){
            bindingResult.addError(new FieldError("memberForm", "name", memberForm.getName(), false, new String[]{"required.memberForm.name"}, null, null));
        }
        if(!StringUtils.hasText(memberForm.getEmail())){
            bindingResult.addError(new FieldError("memberForm", "email", memberForm.getEmail(), false, new String[]{"required.memberForm.email"}, null, null));
        }
        if(memberForm.getProfileImage().isEmpty()){
            bindingResult.addError(new FieldError("memberForm", "profileImage", memberForm.getProfileImage(), false, new String[]{"required.memberForm.profileImage"}, null, null));
        }
        if(!StringUtils.hasText(memberForm.getPasswd())){
            bindingResult.addError(new FieldError("memberForm", "passwd", memberForm.getPasswd(), false, new String[]{"required.memberForm.passwd"}, null, null));
        }
        if(!StringUtils.hasText(memberForm.getRePasswd())){
            bindingResult.addError(new FieldError("memberForm", "rePasswd", memberForm.getRePasswd(), false, new String[]{"required.memberForm.rePasswd"}, null, null));
        }

        // 특정 입력 필드가 아닌 여러 필드에 대한 복합적 데이터 검증 시
        // 예를 들어 쇼핑몰 상품 주문 시 (주문 갯수 * 단가 = 10,000원 이상이어야 하는 경우)
        int inputTotalPrice = 9000;
        if(inputTotalPrice < 10000){
            bindingResult.addError(new ObjectError("memberForm", new String[]{"min.totalprice"}, new Object[]{10000, inputTotalPrice}, null));
        }
        // 입력 데이터 검증 실패 시 Model에 오류 메시지 저장 후 회원 가입화면으로 Forward
        if(bindingResult.hasErrors()){
            //model.addAttribute("bindingResult", bindingResult);
            return "/member/signUpFormV3";
        }

        // 업로드 프로필 사진 저장
        UploadFile uploadFile = fileService.storeFile(memberForm.getProfileImage(), profileFileUploadPath);
        // Form Bean -> Dto 변환
        MemberDto memberDto = MemberDto.builder()
                .id(memberForm.getId())
                .name(memberForm.getName())
                .email(memberForm.getEmail())
                .originalProfile(uploadFile.getUploadFileName())
                .storedProfile(uploadFile.getStoreFileName())
                .passwd(memberForm.getPasswd())
                .build();
        memberService.register(memberDto);
        redirectAttributes.addFlashAttribute("memberDto", memberDto);
        return "redirect:/member/signup/result";
    }

    // 아이디 중복 여부 요청 처리
    @GetMapping("/idcheck/{inputId}")
    public @ResponseBody Map<String, Object> idDupCheckAction(@PathVariable("inputId") String inputId) {
        log.info("요청 아이디 : {}", inputId);

        Map<String, Object> map = new HashMap<>();
        map.put("result", true);
        map.put("message", "사용 가능한 아이디입니다.");

        MemberDto memberDto = memberService.getMember(inputId);
        if (memberDto != null) {
            map.put("result", false);
            map.put("message", "이미 사용중인 아이디입니다.");
        }
        return map;
    }

    // 회원 가입 결과 화면 요청 처리
    @GetMapping("/signup/result")
    public String signUpResult() {
        return "/member/signUpResult";
    }

    // 회원 프로필 사진 요청 처리
    @GetMapping("/image/{profileFileName}")
    @ResponseBody
    public ResponseEntity<Resource> showImage(@PathVariable("profileFileName") String profileFileName) throws IOException {
        Path path = Paths.get(profileFileUploadPath + "/" + profileFileName);
        String contentType = Files.probeContentType(path);
        Resource resource = new FileSystemResource(path);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }

    // 회원 정보 수정 화면 요청 처리
    @GetMapping("/edit/{memberId}")
    public String editForm(@PathVariable("memberId") String memberId, Model model) {
        MemberDto memberDto = memberService.getMember(memberId);
        MemberForm memberForm = MemberForm.builder()
                .id(memberDto.getId())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .regdate(memberDto.getRegdate())
                .build();
        model.addAttribute("memberForm", memberForm);
        return "/member/editForm";
    }

    // 회원 정보 수정 요청 처리
    @PostMapping("/edit/{memberId}")
    public String editAction(@PathVariable("memberId") String memberId, @ModelAttribute MemberForm memberForm, Model model) {
        log.info("회원 수정 정보: {}", memberForm);
        MemberDto memberDto = MemberDto.builder()
                .id(memberForm.getId())
                .email(memberForm.getEmail())
                .passwd(memberForm.getPasswd())
                .build();
        memberService.editMember(memberDto);
        return "redirect:/member/" + memberId;
    }

    // 회원 상세 정보 요청 처리
    @GetMapping("/{memberId}")
    public String readMember(@PathVariable("memberId") String memberId, Model model) {
        MemberDto memberDto = memberService.getMember(memberId);
        model.addAttribute("memberDto", memberDto);
        return "/member/read";
    }

    // 회원 목록 요청 처리
    @GetMapping
    public String listMember(Model model) {
        List<MemberDto> members = memberService.getMembers();
        model.addAttribute("members", members);
        return "/member/list";
    }

    // 회원 로그인 화면 요청 처리
    @GetMapping("/signin")
    public String signInForm(@ModelAttribute LoginForm loginForm) {
        return "/member/signInForm";
    }

    // 회원 로그인 요청 처리
    @PostMapping("/signin")
    public String signInAction(@ModelAttribute LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        log.info("로그인 정보 : {}", loginForm.toString());
        MemberDto loginMember = memberService.isMember(loginForm.getLoginId(), loginForm.getLoginPasswd());
        // 회원 아닌 경우
        if (loginMember == null) {
            return "/member/signInForm";
        }
        // 회원인 경우
        // 아이디 저장 체크 시
        if(loginForm.isRememberLoginId()){
            Cookie saveIdCookie = new Cookie("saveId", loginMember.getId());
            saveIdCookie.setMaxAge(60*60*24*100); // 100일저장
            saveIdCookie.setPath("/");
            response.addCookie(saveIdCookie);
        }else {
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("saveId")){
                        cookie.setPath("/");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        return "redirect:/";
    }

    // 회원 로그아웃 요청 처리
    @GetMapping("/signout")
    public String signInAction(HttpServletRequest request) {
        // 세션이 있으면 기존 세션 반환, 없으면 생성하지 않고 null 반환
        HttpSession session =  request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}





