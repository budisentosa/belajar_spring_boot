package com.example.berzkoder02.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.berzkoder02.dto.CategoryDto;
import com.example.berzkoder02.dto.ResponseData;
import com.example.berzkoder02.dto.SearchString;
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


    @PostMapping("/search/{size}/{page}")
    public Iterable<Category> findByName(@RequestBody SearchString searchString,
            @PathVariable("size") int size, @PathVariable("page") int page) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return categoryService.findByNameContainsPageIterable(searchString.getSearchKey(), pageable);
    }

    @PostMapping("saveBatch")
    public ResponseEntity<ResponseData<Iterable<Category>>> saveBatch(
            @Valid @RequestBody CategoryDto[] categoryDtos, Errors errors) {
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        List<Category> categories = Arrays.asList(categoryDtos)
            .stream()
            .map(tmp -> modelMapper.map(tmp, Category.class))
            .collect(Collectors.toList());

        responseData.setStatus(true);
        responseData.setPayload(categoryService.saveBatch(categories));
        return ResponseEntity.ok(responseData);
    }
}
