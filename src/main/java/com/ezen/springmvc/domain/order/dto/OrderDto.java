package com.ezen.springmvc.domain.order.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class OrderDto {
    private int orderId;
    private String memberId;
    private int orderPrice;
}

