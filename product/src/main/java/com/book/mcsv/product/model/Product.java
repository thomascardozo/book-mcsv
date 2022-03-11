package com.book.mcsv.product.model;

import com.book.mcsv.product.dto.ProductDTO;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Float preco;
    private String descricao;
    private String productIdentifier;

    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public static Product convert(ProductDTO productDTO) {
        Product product = new Product();

        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(
                productDTO.getProductIdentifier());
        if (productDTO.getCategoryDTO() != null) {
            product.setCategory(
                    Category.convert(productDTO.getCategoryDTO()));
        }
        return product;
    }

}
