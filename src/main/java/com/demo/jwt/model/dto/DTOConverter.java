package com.demo.jwt.model.dto;

import com.demo.jwt.model.product.Category;
import com.demo.jwt.model.product.Product;
import com.demo.jwt.model.product.ProductDTO;
import com.demo.jwt.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DTOConverter {
    @Autowired
    private ICategoryService categoryService;

    public Product convertToProduct(ProductDTO productDTO) {
        Optional<Category> category = this.categoryService.findById(productDTO.getCategoryID());
        Product product = new Product(productDTO.getId(), productDTO.getProduct_name(), productDTO.getDescription(), productDTO.getPrice(), category.get());
        return product;
    }

    public ProductDTO convertToProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getProduct_name(), product.getDescription(), product.getPrice(), product.getCategory().getId());
    }
}
