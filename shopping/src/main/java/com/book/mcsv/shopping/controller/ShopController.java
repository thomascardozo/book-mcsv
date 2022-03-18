package com.book.mcsv.shopping.controller;

import com.book.mcsv.shopping.dto.ShopDTO;
import com.book.mcsv.shopping.dto.ShopReportDTO;
import com.book.mcsv.shopping.dto.ShopsDateValueDTO;
import com.book.mcsv.shopping.service.ShopService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@RestController
@Slf4j
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops() {
        List<ShopDTO> shops = shopService.getAll();

        return shops;
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier) {
        List<ShopDTO> shops = shopService.getByUser(userIdentifier);

        return shops;
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        List<ShopDTO> shops = shopService.getByDate(shopDTO);

        return shops;
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(@RequestBody ShopsDateValueDTO shopsDateValueDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime dtInicioConvertida = LocalDateTime.parse(shopsDateValueDTO.dataInicio+" 01:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LocalDateTime dtFimConvertida = null;
        Float vlrMinimo = null;
        if(shopsDateValueDTO.dataFim != null){
            dtFimConvertida = LocalDateTime.parse(shopsDateValueDTO.dataFim+" 01:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        }
        if(shopsDateValueDTO.valorMinimo != null){
            vlrMinimo = shopsDateValueDTO.valorMinimo;
        }

        return shopService.getShopsByFilter(dtInicioConvertida, dtFimConvertida, vlrMinimo);
    }

    @PostMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
//            @RequestParam(name = "dataInicio", required=true)
//            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime dataInicio,
//            @RequestParam(name = "dataFim", required=true)
//            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDateTime dataFim
            @RequestBody ShopsDateValueDTO shopsDateValueDTO) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime dtInicioConvertida = LocalDateTime.parse(shopsDateValueDTO.dataInicio+" 01:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        LocalDateTime dtFimConvertida = LocalDateTime.parse(shopsDateValueDTO.dataFim+" 01:00:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return shopService.getReportByDate(dtInicioConvertida, dtFimConvertida);
    }

    @PostMapping("/shopping")
    public ShopDTO newShop(@RequestHeader(name = "key", required=true) String key,
                           @RequestBody ShopDTO shopDTO) {
        return shopService.save(shopDTO, key);
    }

}
