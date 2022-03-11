package com.book.mcsv.product.dto;

import com.book.mcsv.product.model.Category;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDTO {

    @NotNull
    private Long id;
    private String nome;

    public static CategoryDTO convert(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(category.getId());
        categoryDTO.setNome(category.getNome());
        return categoryDTO;
    }
}
