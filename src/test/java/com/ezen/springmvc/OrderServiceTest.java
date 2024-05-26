package com.ezen.springmvc;

import com.ezen.springmvc.domain.order.dto.OrderDto;
import com.ezen.springmvc.domain.order.exception.NotEnoughMoneyException;
import com.ezen.springmvc.domain.order.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
public class OrderServiceTest {

	@Autowired
	private OrderService orderService;
	
	@Test
	@DisplayName("정상 주문 처리 테스트")
	@Disabled
	public void orderTest() throws NotEnoughMoneyException {
		OrderDto orderDto = OrderDto.builder()
				.memberId("customer2")
				.orderPrice(10000)
				.build();
		orderService.order(orderDto);
	}
	
	@Test
	@DisplayName("시스템(런타임) 예외 발생 주문 처리 테스트")
	@Disabled
	public void orderTest2()  throws NotEnoughMoneyException {
		OrderDto orderDto = OrderDto.builder()
				.memberId("error")
				.orderPrice(10000)
				.build();
		orderService.order(orderDto);
	}

	@Test
	@DisplayName("비즈니스(컴파일 체크) 예외 발생 주문 처리 테스트")
	@Disabled
	public void orderTest3() {
		OrderDto orderDto = OrderDto.builder()
				.memberId("bangry")
				.orderPrice(10000)
				.build();
		try {
		    orderService.order(orderDto);
		} catch (NotEnoughMoneyException e) {
		    log.info("잔액이 부족하여 별도의 계좌로 입금 안내합니다.");
		}
	}
}


