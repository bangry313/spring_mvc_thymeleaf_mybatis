package com.ezen.springmvc.domain.order.service;


import com.ezen.springmvc.domain.order.dto.OrderDto;
import com.ezen.springmvc.domain.order.exception.NotEnoughMoneyException;

public interface OrderService {
    public void order(OrderDto orderDto) throws NotEnoughMoneyException;
}

