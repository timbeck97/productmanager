package com.productmanager.service.product;

import com.productmanager.dto.ProductDTO;
import com.productmanager.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDTO product);
    Product updateProduct(long id, ProductDTO product);
    void deleteProduct(long id);
    Product getProduct(long id);
    List<Product> getAllProducts();
}
