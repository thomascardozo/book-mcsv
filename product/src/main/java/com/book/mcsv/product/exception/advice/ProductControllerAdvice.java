package com.book.mcsv.product.exception.advice;

import com.book.mcsv.product.dto.ProductDTO;
import com.book.shoping.client.dto.ErrorDTO;
import com.book.shoping.client.exception.CategoryNotFoundException;
import com.book.shoping.client.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice(basePackages = "com.book.mcsv.product.controller")
public class ProductControllerAdvice {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorDTO handleProductNotFound(ProductNotFoundException productNotFoundException){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Produto não encontrado.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CategoryNotFoundException.class)
    public ErrorDTO handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Categoria não encontrada.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO processValidationError(MethodArgumentNotValidException methodArgumentNotValidException){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        BindingResult result = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder sb = new StringBuilder("Valor inválido para o(s) campo(s): ");

        for (FieldError fieldError : fieldErrors) {
            sb.append(" - ");
            sb.append(fieldError.getField());
        }

        errorDTO.setMessage(sb.toString());
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }

}
