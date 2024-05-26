package com.ezen.springmvc.domain.order.service;

import com.ezen.springmvc.domain.order.dto.OrderDto;
import com.ezen.springmvc.domain.order.dto.PaymentDto;
import com.ezen.springmvc.domain.order.exception.NotEnoughMoneyException;
import com.ezen.springmvc.domain.order.mapper.OrderMapper;
import com.ezen.springmvc.domain.order.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final PaymentMapper paymentMapper;

    @Transactional
    @Override
    public void order(OrderDto orderDto) throws NotEnoughMoneyException {
        log.info("========= order(OrderDto orderDto) 메소드 호출됨 =========");
        // #1. 주문테이블에 주문 관련 기본 정보 저장
        orderMapper.create(orderDto);

        // #2. 결재 처리
        PaymentDto paymentDto = PaymentDto.builder()
                .orderId(orderDto.getOrderId())
                .payStatus("완료")
                .build();
        log.info("-------------------- 결제 처리 진행 --------------------");
        // 시나리오1 : 회원 아이디가 error 인 경우 테스트를 위해 RuntimeException 발생
        if (orderDto.getMemberId().equals("error")) {
            paymentMapper.create(paymentDto);
            throw new RuntimeException(">>>>>> 시스템(런타임) 예외발생하였습니다."); // 롤백 확인
        } else if (orderDto.getMemberId().equals("bangry")) {
            // 시나리오2 : 회원 아이디가 bangry 인 경우 테스트를 위해 Compile Check Exception 발생
            paymentMapper.create(paymentDto);
            // 요구사항에 따라 주문테이블의 정보를 롤백하지 않고, 결재 테이블의 결재 상태를 대기로 처리
            paymentDto.setPayStatus("대기");
            paymentMapper.updatePayStatus(paymentDto);
            throw new NotEnoughMoneyException(">>>>>> 비즈니스 예외 발생 : "  + orderDto.getMemberId() + "님 잔고가 부족합니다");
        } else {
            // 시나리오3 :  정상 결재 처리
            paymentMapper.create(paymentDto);
            log.info(">>>>>> 주문 및 결재가 정상 처리 되었습니다...");
        }
        log.info("\"-------------------- 결제 처리 완료 --------------------\"");
        log.info("========= OrderServiceImple.order() 메소드 종료됨 =========");
    }
}
