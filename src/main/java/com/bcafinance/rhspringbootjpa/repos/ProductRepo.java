package com.bcafinance.rhspringbootjpa.repos;

import com.bcafinance.rhspringbootjpa.models.Products;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends CrudRepository<Products,Long> {


    Optional<Products> findProductByName(String name);

    //select * from mstProduct where Name LIKE 'LG%'
    List<Products> searchByNameStartsWith(String name);

//    List<Products> ProductsRepository.findByNameEndsWith("Burton");
//    List<Products> findByNameNotContaining(String name);

//    @Query("SELECT p FROM Products p WHERE p.description LIKE %:description%")
//    List<Products> searchByDescriptionLike(@Param("description") String description);

//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
//    List<Products> findByNameNotLike(String name);

//    @Query("SELECT p FROM Products p WHERE p.name LIKE ?1%")
//    List<Products> searchByNameStartsWith(String name);
//    @Query("SELECT p FROM Products p WHERE p.Name LIKE %?#{escape([0])} escape ?#{escapeCharacter()}")
//    List<Products> searchByNameEndsWith(String name)
}