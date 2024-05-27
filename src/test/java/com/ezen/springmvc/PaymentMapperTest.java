package com.ezen.springmvc;

import com.ezen.springmvc.domain.order.dto.OrderDto;
import com.ezen.springmvc.domain.order.dto.PaymentDto;
import com.ezen.springmvc.domain.order.mapper.OrderMapper;
import com.ezen.springmvc.domain.order.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PaymentMapperTest {
	@Autowired
	private PaymentMapper paymentMapper;
	@Test
	//@Disabled
	public void createTest() {
		OrderDto orderDto = OrderDto.builder()
				.memberId("customer2")
				.orderPrice(1000000)
				.build();
		PaymentDto paymentDto = PaymentDto.builder()
				.orderId(2)
				.payStatus("완료")
				.build();
		paymentMapper.create(paymentDto);
		log.info("결재 DB 처리 완료 : {}", paymentDto);
	}
}


