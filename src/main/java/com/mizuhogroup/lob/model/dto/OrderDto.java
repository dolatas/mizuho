package com.mizuhogroup.lob.model.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderDto {
    Long id;
    Double price;
    Character side;
    Long size;
}
