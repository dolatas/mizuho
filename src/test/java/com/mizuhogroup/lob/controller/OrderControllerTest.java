package com.mizuhogroup.lob.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizuhogroup.lob.model.dto.PostOrderRequestDto;
import com.mizuhogroup.lob.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mizuhogroup.lob.TestDataProvider.order;
import static com.mizuhogroup.lob.TestDataProvider.orderDto;
import static com.mizuhogroup.lob.TestDataProvider.postOrderRequestDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldPostOrder() throws Exception {
        //given
        when(orderService.addOrder(any(PostOrderRequestDto.class))).thenReturn(order());

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postOrderRequestDto())))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(orderDto())))
                .andReturn();
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
                PRICE | SIDE | SIZE
                -1    | 'B'  | 1
                0     | 'B'  | 1
                1     | 'A'  | 1
                1     | ''   | 1
                1     | 'B'  | -1
                1     | 'B'  | 0
            """)
    void shouldFailPostOrderWhenRequestIsInvalid(Double price, String side, Long size) throws Exception {
        //given
        PostOrderRequestDto invalidDto = PostOrderRequestDto.builder()
                .price(price)
                .side(side)
                .size(size)
                .build();

        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    void shouldDeleteOrder() {
        //TODO shouldDeleteOrder
    }

    @Test
    void shouldFailDeleteOrder() {
        //TODO shouldFailDeleteOrder
    }

    @Test
    void shouldPatchOrder() {
        //TODO shouldPatchOrder
    }

    @Test
    void shouldFailPatchOrder() {
        //TODO shouldFailPatchOrder
    }

    @Test
    void shouldGetPrice() {
        //TODO shouldGetPrice
    }

    @Test
    void shouldFailGetPrice() {
        //TODO shouldFailGetPrice
    }

    @Test
    void shouldGetSize() {
        //TODO shouldGetSize
    }

    @Test
    void shouldFailGetSize() {
        //TODO shouldFailGetSize
    }

    @Test
    void shouldGetOrders() {
        //TODO shouldGetOrders
    }

    @Test
    void shouldFailGetOrders() {
        //TODO shouldFailGetOrders
    }
}