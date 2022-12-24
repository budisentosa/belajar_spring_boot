package com.example.berzkoder02.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.berzkoder02.dto.ResponseData;
import com.example.berzkoder02.dto.SupplierDto;
import com.example.berzkoder02.models.entities.Supplier;
import com.example.berzkoder02.services.SupplierService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(
            @Valid @RequestBody SupplierDto supplierDto, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);
        }

        // Manual conversion from SupplierDto to Supplier
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierDto.getName());
        // supplier.setAddress(supplierDto.getAddress());
        // supplier.setEmail(supplierDto.getEmail());

        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Supplier>>> getAll() {
        ResponseData<Iterable<Supplier>> responseData = new ResponseData<>();
        Iterable<Supplier> supplier = supplierService.findAll();
        responseData.setStatus(true);
        responseData.setPayload(supplier);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Supplier>> getOne(@PathVariable("id") Long id) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        Supplier supplier = supplierService.findOne(id);
        responseData.setStatus(true);
        responseData.setPayload(supplier);
        return ResponseEntity.ok(responseData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Supplier>> update(@PathVariable("id") Long id,
            @Valid @RequestBody SupplierDto supplierDto, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                responseData.getMessages().add(e.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.badRequest().body(responseData);

        }
        Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));
        return ResponseEntity.ok(responseData);

    }

}
