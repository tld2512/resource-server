package com.demo.jwt.service.product;

import com.demo.jwt.model.dto.DTOConverter;
import com.demo.jwt.model.product.Category;
import com.demo.jwt.model.product.Product;
import com.demo.jwt.model.product.ProductDTO;
import com.demo.jwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductDTO productDTO) {
        this.productRepository.save(this.dtoConverter.convertToProduct(productDTO));
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        this.productRepository.save(this.dtoConverter.convertToProduct(productDTO));
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> findByName(String name) {
        return this.productRepository.findByProductNameContains(name);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return this.productRepository.findAllByCategory(category);
    }

    @Override
    public List<Product> findAllAndSortingAsc() {
        return this.productRepository.findAllAndSortingAsc();
    }

    @Override
    public void delete(String pid) {
        this.productRepository.deleteById(pid);
    }

    @Override
    public boolean isProductIdExisted(String pid) {
        Optional<Product> product = findProductById(pid);
        if (product.isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public Optional<Product> findProductById(String pid) {
        return this.productRepository.findById(pid);
    }


}
