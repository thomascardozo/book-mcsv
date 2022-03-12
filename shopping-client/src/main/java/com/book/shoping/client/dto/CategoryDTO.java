package com.book.shoping.client.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    @NotNull
    private Long id;
    private String nome;

}
