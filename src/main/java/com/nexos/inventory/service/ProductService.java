package com.nexos.inventory.service;

import com.nexos.inventory.enums.Errors;
import com.nexos.inventory.exceptions.CustomException;
import com.nexos.inventory.model.Product;
import com.nexos.inventory.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    public Page<Product> findAll(Predicate predicate, Pageable pageable) {
        return productRepository.findAll(predicate, pageable);
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public Product findOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, Errors.NOT_PRODUCT_FOUND));
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public boolean existsByNameEqualsIgnoreCase(String name) {
        return productRepository.existsByNameEqualsIgnoreCase(name);
    }

    public boolean existsByNameEqualsIgnoreCaseAndIdNotIn(String name, List<Long> idsToExclude) {
        return productRepository.existsByNameEqualsIgnoreCaseAndIdNotIn(name, idsToExclude);
    }

}
