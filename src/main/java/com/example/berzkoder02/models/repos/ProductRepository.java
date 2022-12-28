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



// List<User> findByNameIs(String name);
// List<User> findByNameEquals(String name);
// List<User> findByNameIsNot(String name);
// List<User> findByNameIsNull();
// List<User> findByNameIsNotNull();
// List<User> findByActiveTrue();
// List<User> findByActiveFalse();

// String likePattern = "a%b%c";
// userRepository.findByNameLike(likePattern);
// List<User> findByNameContaining(String infix);
// List<User> findByNameEndingWith(String suffix);
// List<User> findByNameStartingWith(String prefix);


// List<User> findByAgeBetween(Integer startAge, Integer endAge);
// List<User> findByAgeGreaterThan(Integer age);
// List<User> findByAgeGreaterThanEqual(Integer age);
// List<User> findByAgeLessThan(Integer age);
// List<User> findByAgeLessThanEqual(Integer age);

// List<User> findByAgeIn(Collection<Integer> ages);

// List<User> findByNameOrBirthDate(String name, ZonedDateTime birthDate);
// List<User> findByNameOrBirthDateAndActive(String name, ZonedDateTime birthDate, Boolean active);

// List<User> findByNameOrderByNameDesc(String name);
// List<User> findByNameOrderByName(String name);
// List<User> findByNameOrderByNameAsc(String name);

}
