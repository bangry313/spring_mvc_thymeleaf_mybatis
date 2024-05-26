package com.ezen.springmvc.domain.order.mapper;

import com.ezen.springmvc.domain.order.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface OrderMapper {
	public void create(OrderDto orderDto);
}

