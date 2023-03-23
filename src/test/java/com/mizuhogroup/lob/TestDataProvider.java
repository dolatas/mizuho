package com.mizuhogroup.lob;

import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.model.dto.OrderDto;
import com.mizuhogroup.lob.model.dto.PostOrderRequestDto;
import com.mizuhogroup.lob.repository.OrderEntity;

import java.time.Instant;

public class TestDataProvider {

    public static final Long ORDER_ID = 1L;
    public static final Double ORDER_PRICE = 3.5d;
    public static final Character ORDER_SIDE = 'B';
    public static final Long ORDER_SIZE = 10L;

    public static PostOrderRequestDto postOrderRequestDto() {
        return PostOrderRequestDto.builder()
                .price(ORDER_PRICE)
                .side(ORDER_SIDE.toString())
                .size(ORDER_SIZE)
                .build();
    }

    public static Order order() {
        return Order.builder()
                .id(ORDER_ID)
                .price(ORDER_PRICE)
                .side(ORDER_SIDE)
                .size(ORDER_SIZE)
                .build();
    }

    public static OrderEntity orderEntity() {
        return OrderEntity.builder()
                .id(ORDER_ID)
                .price(ORDER_PRICE)
                .side(ORDER_SIDE)
                .size(ORDER_SIZE)
                .createdDate(Instant.now().toEpochMilli())
                .build();
    }

    public static OrderEntity newOrderEntity() {
        return OrderEntity.builder()
                .price(ORDER_PRICE)
                .side(ORDER_SIDE)
                .size(ORDER_SIZE)
                .build();
    }

    public static OrderDto orderDto() {
        return OrderDto.builder()
                .id(ORDER_ID)
                .price(ORDER_PRICE)
                .side(ORDER_SIDE)
                .size(ORDER_SIZE)
                .build();
    }
}
