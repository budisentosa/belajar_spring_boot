package com.example.berzkoder02.models.repos;

import java.util.List;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.berzkoder02.models.entities.Product;
import com.example.berzkoder02.models.entities.Supplier;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContains(String name);
    List<Product> findByName(String name);

    @Query("select p from Product p where p.name like :name")
    List<Product> findProductByName(@PathParam("name") String name);

    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategoryManual(@PathParam("categoryId") Long categoryId);

    @Query("select p from Product p where :supplier member of p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);

}
