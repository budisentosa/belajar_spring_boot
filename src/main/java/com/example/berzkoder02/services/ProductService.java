package com.example.berzkoder02.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.berzkoder02.models.entities.Product;
import com.example.berzkoder02.models.repos.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByNameContains(String name) {
        return productRepository.findByNameContains(name);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

}
