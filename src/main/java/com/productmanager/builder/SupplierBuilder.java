package com.productmanager.builder;

import com.productmanager.model.Supplier;

import java.time.LocalDate;

public class SupplierBuilder {
    Supplier supplier;

    public SupplierBuilder(){
        this.supplier = new Supplier();
    }
    public static SupplierBuilder init(){
        return new SupplierBuilder();
    }
    public SupplierBuilder withId(long id){
        this.supplier.setId(id);
        return this;
    }
    public SupplierBuilder withName(String name){
        this.supplier.setName(name);
        return this;
    }
    public SupplierBuilder withAddress(String address){
        this.supplier.setAddress(address);
        return this;
    }
    public SupplierBuilder withContact(String contact){
        this.supplier.setContact(contact);
        return this;
    }
    public SupplierBuilder withEmail(String email){
        this.supplier.setEmail(email);
        return this;
    }
    public SupplierBuilder withCreatedAt(LocalDate createdAt){
        this.supplier.setCreatedAt(createdAt);
        return this;
    }
    public Supplier build(){
        return this.supplier;
    }
}
