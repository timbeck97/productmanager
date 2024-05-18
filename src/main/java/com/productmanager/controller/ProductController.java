package com.productmanager.controller;

import com.productmanager.dto.ProductDTO;
import com.productmanager.dto.SupplierDTO;
import com.productmanager.model.Product;
import com.productmanager.model.Supplier;
import com.productmanager.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> findAllSuppliers(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @PostMapping
    public ResponseEntity<Product> addSupplier(@Valid @RequestBody ProductDTO productDTO){
        return ResponseEntity.status(201).body(productService.addProduct(productDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateSupplier(@PathVariable  Long id, @Valid @RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
