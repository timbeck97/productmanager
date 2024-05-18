package com.productmanager.service.product;

import com.productmanager.builder.ProductBuilder;
import com.productmanager.dto.ProductDTO;
import com.productmanager.model.Product;
import com.productmanager.repository.ProductRepository;
import com.productmanager.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public ProductServiceImp(ProductRepository productRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Product addProduct(ProductDTO product) {
        return supplierRepository.findById(product.getSupplierId())
                .map(supplier -> productRepository.save(ProductBuilder.init()
                        .withName(product.getName())
                        .withDescription(product.getDescription())
                        .withSupplier(supplier)
                        .withCreatedAt(LocalDate.now())
                        .build())).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public Product updateProduct(long id, ProductDTO product) {
        return supplierRepository.findById(product.getSupplierId())
                .map(supplier -> productRepository.save(ProductBuilder.init()
                        .withId(id)
                        .withName(product.getName())
                        .withDescription(product.getDescription())
                        .withSupplier(supplier)
                        .build())).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.findById(id).map(product -> {
            productRepository.delete(product);
            return product;
        }).orElseThrow(()->new IllegalArgumentException("Product not found"));
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
