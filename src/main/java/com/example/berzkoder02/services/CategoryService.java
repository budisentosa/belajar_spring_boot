package com.example.berzkoder02.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.berzkoder02.models.entities.Category;
import com.example.berzkoder02.models.repos.CategoryRepo;
import jakarta.transaction.TransactionScoped;

@Service
@TransactionScoped
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public Category findById(Long id) {
        Optional<Category> findById = categoryRepo.findById(id);
        if (!findById.isPresent()) {
            return null;
        }
        return findById.get();
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    public Iterable<Category> findByNameContainsPageIterable(String name, Pageable pageable) {
        return categoryRepo.findByNameContains(name, pageable);
    }

    public Iterable<Category> saveBatch(Iterable<Category> categories) {
        return categoryRepo.saveAll(categories);
    }


}
