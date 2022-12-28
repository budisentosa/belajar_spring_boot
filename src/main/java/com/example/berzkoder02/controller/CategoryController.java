package com.example.berzkoder02.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.berzkoder02.dto.CategoryDto;
import com.example.berzkoder02.dto.ResponseData;
import com.example.berzkoder02.models.entities.Category;
import com.example.berzkoder02.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(
            @Valid @RequestBody CategoryDto categoryDto, Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        responseData.setStatus(true);
        responseData.setPayload(categoryService.save(category));
        // return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(Long id) {
        return categoryService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(
            @Valid @RequestBody CategoryDto categoryDto, Errors errors) {

        ResponseData<Category> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        responseData.setStatus(true);
        categoryService.save(categoryService.save(category));
        // return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        return ResponseEntity.ok(responseData);
    }


}
