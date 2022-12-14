package com.example.berzkoder02.models.repos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.berzkoder02.models.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContains(String name);

    List<Product> findByName(String name);
}
