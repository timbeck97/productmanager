package com.productmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.productmanager.model.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;


import java.time.LocalDate;

public class ProductDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotBlank
    private Long supplierId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public ProductDTO() {
    }
    public ProductDTO(Product p){
        BeanUtils.copyProperties(p,this);
        this.supplierId = p.getSupplier().getId();
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
