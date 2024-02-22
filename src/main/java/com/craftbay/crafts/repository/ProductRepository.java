package com.craftbay.crafts.repository;

import com.craftbay.crafts.entity.product.Product;
import com.craftbay.crafts.util.enums.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);

    List<Product> findTop12ByOrderByUpdateDateDesc();

    List<Product> findTop5ByCategory(ProductCategoryEnum category);

    List<Product> findAllByCategory(ProductCategoryEnum category);
}
