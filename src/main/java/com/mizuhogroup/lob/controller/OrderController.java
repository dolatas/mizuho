package com.mizuhogroup.lob.controller;

import com.mizuhogroup.lob.mapper.OrderMapper;
import com.mizuhogroup.lob.model.Order;
import com.mizuhogroup.lob.model.dto.OrderDto;
import com.mizuhogroup.lob.model.dto.PatchOrderRequestDto;
import com.mizuhogroup.lob.model.dto.PostOrderRequestDto;
import com.mizuhogroup.lob.model.dto.PriceDto;
import com.mizuhogroup.lob.model.dto.SizeDto;
import com.mizuhogroup.lob.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto postOrder(@RequestBody @Valid PostOrderRequestDto postOrderRequestDto) {
        log.info("Post order, {}", postOrderRequestDto);
        Order order = orderService.addOrder(postOrderRequestDto);
        return OrderMapper.mapToDto(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable @Positive Long orderId) {
        log.info("Delete order id = {}", orderId);
        orderService.deleteOrder(orderId);
    }

    @PatchMapping("/{orderId}")
    public OrderDto patchOrder(@PathVariable @Positive Long orderId, @Valid @RequestBody PatchOrderRequestDto patchOrderRequestDto) {
        log.info("Patch order id = {}, {}", orderId, patchOrderRequestDto);
        Order order = orderService.modifyOrder(orderId, patchOrderRequestDto);
        return OrderMapper.mapToDto(order);
    }

    @GetMapping("/price")
    public PriceDto getPrice(@RequestParam @NotBlank Character side, @RequestParam @Positive Integer level) {
        return new PriceDto(orderService.findPriceForLevel(side, level));
    }

    @GetMapping("/size")
    public SizeDto getSize(@RequestParam @NotBlank Character side, @RequestParam @Positive Integer level) {
        return new SizeDto(orderService.findTotalSizeForLevel(side, level));
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestParam @NotBlank Character side) {
        return orderService.findOrdersBySide(side).stream().map(OrderMapper::mapToDto).toList();
    }
}
