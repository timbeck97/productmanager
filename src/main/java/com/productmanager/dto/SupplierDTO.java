package com.productmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;


import java.time.LocalDate;

public class SupplierDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String contact;
    @NotBlank
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public SupplierDTO() {
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotBlank String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank String address) {
        this.address = address;
    }

    public @NotBlank String getContact() {
        return contact;
    }

    public void setContact(@NotBlank String contact) {
        this.contact = contact;
    }

    public @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
