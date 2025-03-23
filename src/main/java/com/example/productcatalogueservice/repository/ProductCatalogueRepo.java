package com.example.productcatalogueservice.repository;

import com.example.productcatalogueservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCatalogueRepo extends JpaRepository<Product , Long> {
    @Override
    Optional<Product> findById(Long aLong);

    @Override
    List<Product> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    <S extends Product> S save(S entity);


}
