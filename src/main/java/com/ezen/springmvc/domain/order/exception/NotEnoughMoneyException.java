package com.ezen.springmvc.domain.order.exception;

// 사용자 정의 컴파일 체크 예외 (비즈니스 예외)
public class NotEnoughMoneyException extends Exception{
	public NotEnoughMoneyException() {
		super("잔액이 부족합니다.");
	}
	public NotEnoughMoneyException(String message) {
		super(message);
	}
}

