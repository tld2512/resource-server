package com.demo.jwt.controller;

import com.demo.jwt.model.product.Category;
import com.demo.jwt.model.product.Product;
import com.demo.jwt.model.product.ProductDTO;
import com.demo.jwt.service.category.ICategoryService;
import com.demo.jwt.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/productList")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products = this.productService.findAll();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/productList/search")
    public ResponseEntity<List<Product>> getProductListByName(@RequestParam("name") String name) {
        List<Product> products = this.productService.findByName(name);
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/productList/category")
    public ResponseEntity<List<Product>> getProductListByCategory(@RequestParam("cid") String cid) {
        Optional<Category> category = this.categoryService.findById(cid);
        if (category.isPresent()) {
            return new ResponseEntity<>(this.productService.findByCategory(category.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO product) {
        if (this.productService.isProductIdExisted(product.getId())) {
            return new ResponseEntity<>("Id already existed", HttpStatus.IM_USED);
        }
        this.productService.addProduct(product);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    @PutMapping("/update/{pid}")
    public void updateProduct(@RequestBody ProductDTO product) {
        this.productService.updateProduct(product);
    }

    @GetMapping("/productList/price-asc")
    public ResponseEntity<List<Product>> getProductListOrderByPriceAsc() {
        List<Product> products = this.productService.findAllAndSortingAsc();
        if (!products.isEmpty()) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<String> deleteProduct(@PathVariable("pid") String pid) {
        if (!this.productService.isProductIdExisted(pid)) {
            return new ResponseEntity<>("Id not existed", HttpStatus.NO_CONTENT);
        }
        this.productService.delete(pid);
        return new ResponseEntity<>("delete successful", HttpStatus.ACCEPTED);
    }
}
