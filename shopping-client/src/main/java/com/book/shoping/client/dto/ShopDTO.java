package com.book.shoping.client.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

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

}
