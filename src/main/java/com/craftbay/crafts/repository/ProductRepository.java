package com.craftbay.crafts.repository;

import com.craftbay.crafts.dto.product.ProductRequestDto;
import com.craftbay.crafts.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);

    Product save(ProductRequestDto productRequestDto);
}
