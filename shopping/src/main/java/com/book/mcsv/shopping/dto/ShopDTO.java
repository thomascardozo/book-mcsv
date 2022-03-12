package com.book.mcsv.shopping.dto;

import com.book.mcsv.shopping.model.Item;
import com.book.mcsv.shopping.model.Shop;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    //@NotNull
    private Float total;
    //@NotNull
    private LocalDateTime date;
    @NotNull
    private List<ItemDTO> items;

    public static ShopDTO convert(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        shopDTO.setItems(shop
                .getItems()
                .stream()
                .map(ItemDTO::convert)
                .collect(Collectors.toList()));

        return shopDTO;
    }
}
