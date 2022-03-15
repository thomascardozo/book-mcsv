package com.book.mcsv.product.controller;

import com.book.mcsv.product.dto.ProductDTO;
import com.book.mcsv.product.service.ProductService;
import com.book.shoping.client.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public List<ProductDTO> getProducts() {
        List<ProductDTO> produtos = productService.getAll();
        return produtos;
    }

    @GetMapping("/product/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(@PathVariable Long categoryId) {
        List<ProductDTO> produtos = productService.getProductByCategoryId(categoryId);
        return produtos;
    }

    @GetMapping("/product/{productIdentifier}")
    ProductDTO findByProductIdentifier(@PathVariable String productIdentifier) {
        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping("/product")
    ProductDTO newProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @DeleteMapping("/product/{id}")
    public String delete(@PathVariable Long id) throws ProductNotFoundException {
        productService.delete(id);
        return "Deletado";
    }
}
