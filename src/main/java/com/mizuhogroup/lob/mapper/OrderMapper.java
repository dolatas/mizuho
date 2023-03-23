package com.mizuhogroup.lob.mapper;

import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.model.dto.OrderDto;
import com.mizuhogroup.lob.repository.OrderEntity;

public class OrderMapper {

    public static OrderDto mapToDto(Order order) {
        if (order == null) {
            return null;
        }
        return OrderDto.builder()
                .id(order.getId())
                .price(order.getPrice())
                .size(order.getSize())
                .side(order.getSide())
                .build();
    }

    public static Order mapToOrder(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        return Order.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .size(entity.getSize())
                .side(entity.getSide())
                .build();
    }
}
