package com.mizuhogroup.lob.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PostOrderRequestDto {
    @NotNull
    @Positive
    Double price;

    @NotBlank
    @Pattern(regexp = "[BO]")
    String side;

    @NotNull
    @Positive
    Long size;
}
