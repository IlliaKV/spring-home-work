package com.vebdev.springhomework.repository;

import com.vebdev.springhomework.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("from Product p where p.nameProduct=:productName")
    List<Product> findProductsByName(@Param("productName") String productName);

    @Query("from Product p where p.manufacturer.id=:manufacturerId")
    List<Product> getProductsByManufacturerId(@Param("manufacturerId") long manufacturerId);
}
