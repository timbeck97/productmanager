package com.productmanager.service.supplier;

import com.productmanager.builder.SupplierBuilder;
import com.productmanager.config.MessageSender;
import com.productmanager.dto.SupplierDTO;
import com.productmanager.model.Supplier;
import com.productmanager.repository.SupplierRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
@Service
public class SupplierServiceImp implements SupplierService{
    private final Logger log = LogManager.getLogger(this.getClass().getName());

    private final SupplierRepository supplierRepository;
    private final MessageSender messageSender;

    public SupplierServiceImp(SupplierRepository supplierRepository, MessageSender messageSender) {
        this.supplierRepository = supplierRepository;
        this.messageSender = messageSender;
    }

    @Override
    public Supplier addSupplier(SupplierDTO supplier) {
        Supplier sup = supplierRepository.save(SupplierBuilder.init().withName(supplier.getName())
                .withAddress(supplier.getAddress())
                .withContact(supplier.getContact())
                .withEmail(supplier.getEmail())
                .withCreatedAt(LocalDate.now())
                .build());
        log.info("Supplier added: "+sup.getName());
        messageSender.publishMessage(sup);
        return sup;
    }

    @Override
    public Supplier updateSupplier(long id, SupplierDTO supplier) {
        return supplierRepository.findById(id).map(supplier1 -> {
            supplier1.setName(supplier.getName());
            supplier1.setAddress(supplier.getAddress());
            supplier1.setContact(supplier.getContact());
            supplier1.setEmail(supplier.getEmail());
            Supplier sup = supplierRepository.save(supplier1);
            log.info("Supplier updated: "+sup.getName());
            messageSender.publishMessage(sup);
            return sup;
        }).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public void deleteSupplier(long id) {
        supplierRepository.findById(id).map(supplier -> {
            supplierRepository.delete(supplier);
            log.info("Supplier deleted: "+supplier.getName());
            return supplier;
        }).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public Supplier getSupplier(long id) {
        return supplierRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Supplier not found"));
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}
