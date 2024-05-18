package com.productmanager.dto;

import com.productmanager.model.Supplier;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class ProductDTO {

    private String name;

    private String description;

    private LocalDate createdAt;

    private Long supplierId;

    public ProductDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
