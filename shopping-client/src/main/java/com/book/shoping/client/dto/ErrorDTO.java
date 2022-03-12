package com.book.shoping.client.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDTO {

    private int status;
    private String message;
    private LocalDateTime timestamp;
}
