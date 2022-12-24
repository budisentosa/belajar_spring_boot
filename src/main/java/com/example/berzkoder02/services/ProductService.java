package com.example.berzkoder02.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.berzkoder02.models.entities.Product;
import com.example.berzkoder02.models.entities.Supplier;
import com.example.berzkoder02.models.repos.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        Optional<Product> tmp = productRepository.findById(id);
        if (!tmp.isPresent()) {
            return null;
        }
        return tmp.get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Product> tmp = productRepository.findById(id);
        if (tmp.isPresent()) {
            productRepository.deleteById(id);
        }
    }

    public List<Product> findByNameContains(String name) {
        return productRepository.findByNameContains(name);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void addSupplier(Supplier supplier,Long productId) {
        Product product = findById(productId);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

}
