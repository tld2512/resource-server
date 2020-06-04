package com.demo.jwt.service.product;

import com.demo.jwt.model.product.Category;
import com.demo.jwt.model.product.Product;
import com.demo.jwt.model.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    void addProduct(ProductDTO product);

    void updateProduct(ProductDTO product);

    List<Product> findAll();

    List<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findAllAndSortingAsc();

    void delete(String pid);

    boolean isProductIdExisted(String pid);

    Optional<Product> findProductById(String pid);
}
