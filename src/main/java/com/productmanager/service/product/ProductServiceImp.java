package com.productmanager.service.product;

import com.productmanager.builder.ProductBuilder;
import com.productmanager.config.MessageSender;
import com.productmanager.dto.ProductDTO;
import com.productmanager.model.Product;
import com.productmanager.model.Supplier;
import com.productmanager.repository.ProductRepository;
import com.productmanager.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService{
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final MessageSender messageSender;

    public ProductServiceImp(ProductRepository productRepository, SupplierRepository supplierRepository, MessageSender messageSender) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.messageSender = messageSender;
    }

    @Override
    public Product addProduct(ProductDTO product) {
        return supplierRepository.findById(product.getSupplierId())
                .map(supplier -> {
                    Product prod = productRepository.save(ProductBuilder.init()
                            .withName(product.getName())
                            .withDescription(product.getDescription())
                            .withSupplier(supplier)
                            .withCreatedAt(LocalDate.now())
                            .build());
                    messageSender.publishMessage(prod);
                    return prod;
                }).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public Product updateProduct(long id, ProductDTO product) {
        return supplierRepository.findById(product.getSupplierId())
                .map(supplier -> {
                    Product prod = productRepository.save(ProductBuilder.init()
                            .withId(id)
                            .withName(product.getName())
                            .withDescription(product.getDescription())
                            .withSupplier(supplier)
                            .build());
                    messageSender.publishMessage(prod);
                    return prod;
                }).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
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
