package com.mizuhogroup.lob.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {
    Long id;
    Double price;
    Character side;
    Long size;
}