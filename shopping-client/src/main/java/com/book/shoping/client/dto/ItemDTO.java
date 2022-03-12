package com.book.shoping.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    @NotNull
    private Float price;
}
