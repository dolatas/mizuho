package com.mizuhogroup.lob.service;

import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.repository.OrderEntity;
import com.mizuhogroup.lob.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static com.mizuhogroup.lob.TestDataProvider.ORDER_ID;
import static com.mizuhogroup.lob.TestDataProvider.ORDER_PRICE;
import static com.mizuhogroup.lob.TestDataProvider.ORDER_SIDE;
import static com.mizuhogroup.lob.TestDataProvider.ORDER_SIZE;
import static com.mizuhogroup.lob.TestDataProvider.newOrderEntity;
import static com.mizuhogroup.lob.TestDataProvider.postOrderRequestDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldAddOrder() {
        //given
        OrderEntity newOrderEntity = newOrderEntity();
        OrderEntity savedOrderEntity = newOrderEntity.toBuilder()
                .id(ORDER_ID)
                .createdDate(Instant.now().toEpochMilli())
                .build();
        when(orderRepository.save(any(OrderEntity.class)))
                .thenReturn(savedOrderEntity);

        //when
        Order order = orderService.addOrder(postOrderRequestDto());

        //then
        verify(orderRepository).save(newOrderEntity);
        assertThat(order).isNotNull();
        assertAll(
                () -> assertThat(order.getId()).isEqualTo(ORDER_ID),
                () -> assertThat(order.getPrice()).isEqualTo(ORDER_PRICE),
                () -> assertThat(order.getSide()).isEqualTo(ORDER_SIDE),
                () -> assertThat(order.getSize()).isEqualTo(ORDER_SIZE)
        );
    }

    @Test
    void shouldDeleteOrder() {
        orderService.deleteOrder(ORDER_ID);

        verify(orderRepository).deleteById(ORDER_ID);
    }

    @Test
    void shouldFailToDeleteOrderWhenOrderDoesNotExist() {
        //TODO shouldFailToDeleteOrderWhenOrderDoesNotExist
    }

    @Test
    void shouldModifyOrder() {
        //TODO shouldModifyOrder
    }

    @Test
    void shouldFailToModifyOrderWhenOrderDoesNotExist() {
        //TODO shouldFailToModifyOrderWhenOrderDoesNotExist
    }

    @Test
    void shouldFindPriceForLevel() {
        //TODO shouldFindPriceForLevel
    }

    @Test
    void shouldFailToFindPriceForLevelWhenOrderDoesNotExist() {
        //TODO shouldFailToFindPriceForLevelWhenOrderDoesNotExist
    }

    @Test
    void shouldFindSizeForLevel() {
        //TODO shouldFindSizeForLevel
    }

    @Test
    void shouldFailToFindSizeForLevelWhenOrderDoesNotExist() {
        //TODO shouldFailToFindSizeForLevelWhenOrderDoesNotExist
    }

    @Test
    void shouldFindOrdersBySide() {
        //TODO shouldFindOrdersBySide
    }
}