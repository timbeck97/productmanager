package com.productmanager.service.supplier;

import com.productmanager.dto.SupplierDTO;
import com.productmanager.model.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier addSupplier(SupplierDTO supplier);
    Supplier updateSupplier(long id, SupplierDTO supplier);
    void deleteSupplier(long id);
    Supplier getSupplier(long id);
    List<Supplier> getAllSuppliers();
}
