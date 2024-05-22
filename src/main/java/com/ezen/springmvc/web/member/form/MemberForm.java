package com.ezen.springmvc.web.member.form;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
/**
 * 회원 가입 폼에 대응하는 Form 클래스
 * 스프링 Bean Validator 적용
 */
public class MemberForm {
    @NotBlank(message = "아이디는 필수 입력 항목입니다.")
    @Length(min = 6, max = 10, message = "{min} ~ {max}자 사이의 아이디를 입력하여 주세요.")
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]{5,9}$", message = "아이디 형식이 유효하지 않습니다.")
    private String id;
    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    private String email;
    //@NotNull(message = "프로필 이미지는 필수 입력 항목입니다.")
    private MultipartFile profileImage;
    @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
    private String passwd;
    @NotBlank(message = "비밀번호 확인은 필수 입력 항목입니다.")
    private String rePasswd;
    private String regdate;
}

