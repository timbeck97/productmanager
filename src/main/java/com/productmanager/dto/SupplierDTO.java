package com.productmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public class SupplierDTO {

    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String contact;
    @NotEmpty
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public SupplierDTO() {
    }

    public @NotEmpty String getName() {
        return name;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public @NotEmpty String getAddress() {
        return address;
    }

    public void setAddress(@NotEmpty String address) {
        this.address = address;
    }

    public @NotEmpty String getContact() {
        return contact;
    }

    public void setContact(@NotEmpty String contact) {
        this.contact = contact;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
