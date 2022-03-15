package com.book.mcsv.product.service;

import com.book.mcsv.product.converter.DTOConverter;
import com.book.mcsv.product.dto.ProductDTO;
import com.book.mcsv.product.model.Product;
import com.book.mcsv.product.repository.CategoryRepository;
import com.book.mcsv.product.repository.ProductRepository;
import com.book.shoping.client.exception.CategoryNotFoundException;

import com.book.shoping.client.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = productRepository.getProductByCategory(categoryId);
        return products
                .stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {
        Product product = productRepository.findByProductIdentifier(productIdentifier);
        if (product != null) {
            return DTOConverter.convert(product);
        }
        throw new ProductNotFoundException();

    }

    public ProductDTO save(ProductDTO productDTO) {

        Boolean existsCategory = categoryRepository.existsById(productDTO.getCategoryDTO().getId());
        if (!existsCategory) {
            throw new CategoryNotFoundException();
        }

        Product product = productRepository.save(Product.convert(productDTO));

        return DTOConverter.convert(product);
    }

    public void delete(long productId) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            productRepository.delete(product.get());
        }
        throw new ProductNotFoundException();
    }





}
