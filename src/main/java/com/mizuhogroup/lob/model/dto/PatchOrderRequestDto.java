package com.mizuhogroup.lob.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class PatchOrderRequestDto {

    @NotNull
    @Positive
    Long size;
}
