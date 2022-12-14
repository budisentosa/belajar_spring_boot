package com.example.berzkoder02.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.berzkoder02.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
