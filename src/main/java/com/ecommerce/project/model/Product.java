package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    @NotBlank(message = "Product name is mandatory.")
    private String productName;

    @NotBlank(message = "Product Description is mandatory.")
    private String productDescription;

    @NotNull(message = "Price is mandatory.")
    private BigDecimal productPrice;

    @NotBlank(message = "Category name is mandatory.")
    private String productCategoryName;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryID; // Foreign key referencing Category

    @ManyToOne // Many products can belong to one category
    @JoinColumn(name = "category_id", insertable = false, updatable = false) // Reference to the Category
    private Category category; // Category associated with this product

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }
}
