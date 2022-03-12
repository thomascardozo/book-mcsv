package com.book.mcsv.shopping.model;

import com.book.mcsv.shopping.dto.ShopDTO;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity(name="shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userIdentifier;
    private float total;
    private LocalDateTime date;
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    private List<Item> items;

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public static Shop convert(ShopDTO shoppDTO) {
        Shop shopp = new Shop();

        shopp.setUserIdentifier(shoppDTO.getUserIdentifier());
        shopp.setTotal(shoppDTO.getTotal());
        shopp.setDate(shoppDTO.getDate());
        shopp.setItems(shoppDTO
                .getItems()
                .stream()
                .map(Item::convert)
                .collect(Collectors.toList()));

        return shopp;
    }
}
