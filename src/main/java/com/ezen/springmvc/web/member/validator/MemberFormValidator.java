package com.ezen.springmvc.web.member.validator;

import com.ezen.springmvc.web.member.form.MemberForm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
public class MemberFormValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz) {
		//Class.isAssignableFrom() 메서드는 특정 Class가 어떤 클래스를 상속했는지
		// 또는 어떤 인터페이스를 구현했는지 체크하는 메서드이다.
		return MemberForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		MemberForm memberForm = (MemberForm) target;
		// 폼 필드 검증
		if (!StringUtils.hasText(memberForm.getId())) {
			errors.rejectValue("id", "required");
		} else {
			if(memberForm.getId().length() < 6 || memberForm.getId().length() > 10){
				errors.rejectValue("id", "range", new Object[]{6, 10}, null);
			}
		}
		if(!StringUtils.hasText(memberForm.getName())){
			errors.rejectValue("name", "required");
		}

		if(!StringUtils.hasText(memberForm.getEmail())){
			errors.rejectValue("email", "required");
		}
		if(memberForm.getProfileImage().isEmpty()){
			errors.rejectValue("profileImage", "required");
		}
		if(!StringUtils.hasText(memberForm.getPasswd())){
			errors.rejectValue("passwd", "required");
		}
		if(!StringUtils.hasText(memberForm.getRePasswd())){
			errors.rejectValue("rePasswd", "required");
		}
		// 특정 입력 필드가 아닌 여러 필드에 대한 복합적 데이터 검증 시
		// 예를 들어 쇼핑몰 상품 주문 시 (주문 갯수 * 단가 = 10,000원 이상이어야 하는 경우)
		int inputTotalPrice = 9000;
		if(inputTotalPrice < 10000){
			errors.reject("min.totalprice", new Object[]{10000, inputTotalPrice}, null);
		}
	}
}

