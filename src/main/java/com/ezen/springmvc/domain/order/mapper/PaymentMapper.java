package com.ezen.springmvc.domain.order.mapper;

import com.ezen.springmvc.domain.order.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface PaymentMapper {
	public void create(PaymentDto paymentDto);
	public void updatePayStatus(PaymentDto paymentDto);
}

