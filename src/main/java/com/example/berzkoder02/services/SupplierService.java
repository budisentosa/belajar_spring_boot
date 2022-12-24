package com.example.berzkoder02.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.berzkoder02.models.entities.Supplier;
import com.example.berzkoder02.models.repos.SupplierRepo;
import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplierOptional = supplierRepo.findById(id);
        if (!supplierOptional.isPresent()) {
            return null;
        }
        return supplierOptional.get();
    }

    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public void delete(Long id) {
        supplierRepo.deleteById(id);
    }
}
