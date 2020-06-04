package com.demo.jwt.repository;

import com.demo.jwt.model.product.Category;
import com.demo.jwt.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p where p.product_name like %:name%")
    List<Product> findByProductNameContains(@Param("name") String name);

    List<Product> findAllByCategory(Category category);

    @Query("FROM Product order by price asc")
    List<Product> findAllAndSortingAsc();
}
