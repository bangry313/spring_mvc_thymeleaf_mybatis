package com.ezen.springmvc.web.member.form;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 회원 가입 폼에 대응하는 Form 클래스
 */
public class MemberForm {
    private String id;
    private String name;
    private String email;
    private MultipartFile profileImage;
    private String passwd;
    private String rePasswd;
    private String regdate;
}

