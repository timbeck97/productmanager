package com.productmanager.builder;

import com.productmanager.model.Product;
import com.productmanager.model.Supplier;

import java.time.LocalDate;

public class ProductBuilder {
    Product product;


    public ProductBuilder(){
        this.product = new Product();
    }
    public static ProductBuilder init(){
        return new ProductBuilder();
    }
    public ProductBuilder withId(long id){
        this.product.setId(id);
        return this;
    }
    public ProductBuilder withName(String name){
        this.product.setName(name);
        return this;
    }
    public ProductBuilder withDescription(String description){
        this.product.setDescription(description);
        return this;
    }
    public ProductBuilder withSupplier(Supplier sup){
        this.product.setSupplier(sup);
        return this;
    }
    public ProductBuilder withCreatedAt(LocalDate l){
        this.product.setCreatedAt(l);
        return this;
    }
    public Product build(){
        return this.product;
    }
}
