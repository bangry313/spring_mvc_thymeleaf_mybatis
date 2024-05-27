package com.ezen.springmvc;

import com.ezen.springmvc.domain.order.dto.OrderDto;
import com.ezen.springmvc.domain.order.mapper.OrderMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class OrderMapperTest {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Test
//	@Disabled
	public void createTest() {
		OrderDto orderDto = OrderDto.builder()
				.memberId("customer2")
				.orderPrice(1000000)
				.build();
		orderMapper.create(orderDto);
		log.info("주문 DB 처리 완료 : {}", orderDto);
	}
}


