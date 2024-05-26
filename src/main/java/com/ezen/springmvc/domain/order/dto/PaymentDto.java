package com.ezen.springmvc.domain.order.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentDto {
    private int paymentId;
    private int orderId;
    private String payStatus;
}

