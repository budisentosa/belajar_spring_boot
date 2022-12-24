package com.example.berzkoder02.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.berzkoder02.models.entities.Supplier;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {

}
