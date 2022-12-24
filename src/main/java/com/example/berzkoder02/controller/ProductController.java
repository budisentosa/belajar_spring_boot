package com.example.berzkoder02.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.berzkoder02.dto.ResponseData;
import com.example.berzkoder02.models.entities.Product;
import com.example.berzkoder02.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product,
            Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/search/{name}")
    public Iterable<Product> findByName(@PathVariable("name") String name) {
        return productService.findByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> barufindById(@PathVariable("id") Long id) {
        ResponseData<Product> responseData = new ResponseData<>();
        Product product = productService.findById(id);
        if (product == null) {
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(product);
        return ResponseEntity.ok().body(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Product>> update(@PathVariable("id") Long id,
            @Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }
        Product tmProduct = productService.findById(id);
        if (tmProduct == null) {
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        if (product.getName() != null) {
            tmProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            tmProduct.setDescription(product.getDescription());
        }
        if (product.getCategory() != null) {
            tmProduct.setCategory(product.getCategory());
        }
        tmProduct.setPrice(product.getPrice());
        responseData.setStatus(true);
        responseData.setPayload(productService.save(tmProduct));
        return ResponseEntity.ok().body(responseData);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        productService.delete(id);
    }



}
