package com.mizuhogroup.lob.mapper;

import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.model.dto.OrderDto;
import com.mizuhogroup.lob.repository.OrderEntity;
import org.junit.jupiter.api.Test;

import static com.mizuhogroup.lob.TestDataProvider.order;
import static com.mizuhogroup.lob.TestDataProvider.orderEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderMapperTest {

    @Test
    void shouldMapToOrderDto() {
        // given
        Order order = order();

        // when
        OrderDto orderDto = OrderMapper.mapToDto(order);

        // then
        assertThat(orderDto).isNotNull();
        assertAll(
                () -> assertThat(orderDto.getId()).isEqualTo(order.getId()),
                () -> assertThat(orderDto.getPrice()).isEqualTo(order.getPrice()),
                () -> assertThat(orderDto.getSize()).isEqualTo(order.getSize()),
                () -> assertThat(orderDto.getSide()).isEqualTo(order.getSide())
        );
    }

    @Test
    void shouldMapToNullWhenOrderIsNull() {
        // given
        Order order = null;

        // when
        OrderDto orderDto = OrderMapper.mapToDto(order);

        // then
        assertThat(orderDto).isNull();
    }

    @Test
    void shouldMapToOrder() {
        // given
        OrderEntity orderEntity = orderEntity();

        // when
        Order order = OrderMapper.mapToOrder(orderEntity);

        // then
        assertThat(order).isNotNull();
        assertAll(
                () -> assertThat(order.getId()).isEqualTo(orderEntity.getId()),
                () -> assertThat(order.getPrice()).isEqualTo(orderEntity.getPrice()),
                () -> assertThat(order.getSize()).isEqualTo(orderEntity.getSize()),
                () -> assertThat(order.getSide()).isEqualTo(orderEntity.getSide())
        );
    }

    @Test
    void shouldMapToNullWhenOrderEntityIsNull() {
        // given
        Order order = null;

        // when
        OrderDto orderDto = OrderMapper.mapToDto(order);

        // then
        assertThat(orderDto).isNull();
    }
}