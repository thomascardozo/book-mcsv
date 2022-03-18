package com.book.mcsv.shopping.service;

import com.book.mcsv.shopping.dto.DTOConverter;
import com.book.mcsv.shopping.dto.ItemDTO;
import com.book.mcsv.shopping.dto.ShopDTO;
import com.book.mcsv.shopping.dto.ShopReportDTO;
import com.book.mcsv.shopping.model.Shop;
import com.book.mcsv.shopping.repository.ShopRepository;
import com.book.shoping.client.dto.ProductDTO;
import com.book.shoping.client.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    public List<ShopDTO> getAll() {
        List<Shop> shops = shopRepository.findAll();

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        List<Shop> shops = shopRepository .findAllByUserIdentifier(userIdentifier);

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());

        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if (shop.isPresent()) {
            return ShopDTO.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {

        UserDTO userDTO = userService.getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());
        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item : items) {

            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());

            if (productDTO == null) {
                return false;
            }

            item.setPrice(productDTO.getPreco());
        }
        return true;
    }

    public List<ShopDTO> getShopsByFilter(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo) {

        List<Shop> shops = shopRepository.getShopByFilters(dataInicio, dataFim, valorMinimo);

        return shops
                .stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return shopRepository
                .getReportByDate(dataInicio, dataFim);
    }

}
