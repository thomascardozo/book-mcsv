package com.book.shoping.client.dto;

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
