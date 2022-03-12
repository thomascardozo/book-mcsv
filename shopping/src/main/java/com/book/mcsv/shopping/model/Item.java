package com.book.mcsv.shopping.model;

import com.book.mcsv.shopping.dto.ItemDTO;
import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class Item {

    private String productIdentifier;
    private Float price;

    public static Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setProductIdentifier(
                itemDTO.getProductIdentifier());
        item.setPrice(itemDTO.getPrice());
        return item;
    }
}