package com.book.mcsv.shopping.repository;

import com.book.mcsv.shopping.dto.ShopReportDTO;
import com.book.mcsv.shopping.model.Shop;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository {

    public List<Shop> getShopByFilters(LocalDateTime dataInicio, LocalDateTime dataFim, Float valorMinimo);

    public ShopReportDTO getReportByDate(LocalDateTime dataInicio, LocalDateTime dataFim);
}
