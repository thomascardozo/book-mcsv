package com.book.mcsv.shopping.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class ShopsDateValueDTO {

    public String dataInicio;
    @Nullable
    public String dataFim;
    @Nullable
    public Float valorMinimo;

}
