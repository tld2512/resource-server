package com.demo.jwt.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @NotNull(message = "Please enter product ID")
    private String id;

    @Column(name = "product_name")
    @NotNull(message = "Please enter product name")
    @Size(min = 3, message = "Product name must longer than 3 character")
    private String product_name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Please enter product price")
    private Double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String id, String product_name, String description, Double price, Category category) {
        this.id = id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.category = category;
    }
}
