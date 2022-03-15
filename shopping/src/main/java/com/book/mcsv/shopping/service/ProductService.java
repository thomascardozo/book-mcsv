package com.book.mcsv.shopping.service;

import com.book.shoping.client.dto.ProductDTO;
import com.book.shoping.client.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Value("${PRODUCT_API_URL:http://localhost:8081/product/}")
    private String productApiURL;

    public ProductDTO getProductByIdentifier(String productIdentifier) throws ProductNotFoundException{

        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = productApiURL + productIdentifier;
            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);

            if (response.getStatusCode().is4xxClientError()) {
                throw new ProductNotFoundException();
            }

            return response.getBody();
        } catch (HttpClientErrorException.NotFound e){
            throw new ProductNotFoundException();
        } catch (HttpServerErrorException ex){
            throw new ProductNotFoundException();
        }

    }
}
