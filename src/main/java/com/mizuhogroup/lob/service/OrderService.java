package com.mizuhogroup.lob.service;

import com.mizuhogroup.lob.exception.OrderNotFoundException;
import com.mizuhogroup.lob.mapper.OrderMapper;
import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.model.dto.PatchOrderRequestDto;
import com.mizuhogroup.lob.model.dto.PostOrderRequestDto;
import com.mizuhogroup.lob.repository.OrderEntity;
import com.mizuhogroup.lob.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public Order addOrder(PostOrderRequestDto postOrderRequestDto) {
        OrderEntity orderEntity = OrderEntity.builder()
                .price(postOrderRequestDto.getPrice())
                .size(postOrderRequestDto.getSize())
                .side(postOrderRequestDto.getSide().charAt(0))
                .build();
        orderEntity = orderRepository.save(orderEntity);
        return OrderMapper.mapToOrder(orderEntity);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public Order modifyOrder(Long orderId, PatchOrderRequestDto patchOrderRequestDto) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id=%d not found".formatted(orderId)));
        orderEntity.setSize(patchOrderRequestDto.getSize());
        orderRepository.save(orderEntity);
        return OrderMapper.mapToOrder(orderEntity);
    }

    public Double findPriceForLevel(Character side, Integer level) {
        Map<Double, List<OrderEntity>> orders = orderRepository.findBySide(side)
                .stream()
                .collect(groupingBy(OrderEntity::getPrice, LinkedHashMap::new, Collectors.toList()));
        return orders.keySet().stream().skip(level - 1).findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Order for side=%s and level=%d not found".formatted(side, level)));
    }

    public Long findTotalSizeForLevel(Character side, Integer level) {
        Map<Double, Long> orders = orderRepository.findBySide(side)
                .stream()
                .collect(groupingBy(OrderEntity::getPrice, LinkedHashMap::new, summingLong(OrderEntity::getSize)));
        return orders.values().stream().skip(level - 1).findFirst()
                .orElseThrow(() -> new OrderNotFoundException("Order for side=%s and level=%d not found".formatted(side, level)));
    }

    public List<Order> findOrdersBySide(Character side) {
        return orderRepository.findBySide(side)
                .stream()
                .map(OrderMapper::mapToOrder)
                .toList();
    }
}
