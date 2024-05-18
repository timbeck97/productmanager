package com.productmanager.controller;

import com.productmanager.dto.SupplierDTO;
import com.productmanager.model.Supplier;
import com.productmanager.service.supplier.SupplierService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supplier")
public class SupplierController {


    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity<List<Supplier>> findAllSuppliers(){
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }
    @PostMapping
    public ResponseEntity<Supplier> addSupplier(@Valid @RequestBody  SupplierDTO supplier){
        return ResponseEntity.status(201).body(supplierService.addSupplier(supplier));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable  Long id, @Valid @RequestBody SupplierDTO supplier){
        return ResponseEntity.ok(supplierService.updateSupplier(id, supplier));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
