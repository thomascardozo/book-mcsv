package com.book.mcsv.shopping.dto;

import com.book.mcsv.shopping.model.Item;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemDTO {

    @NotBlank
    private String productIdentifier;
    //@NotNull
    private Float price;

    public static ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setProductIdentifier(
                item.getProductIdentifier());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
}
