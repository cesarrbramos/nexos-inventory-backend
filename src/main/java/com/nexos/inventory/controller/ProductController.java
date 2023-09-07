package com.nexos.inventory.controller;

import com.nexos.inventory.dtos.ProductDTO;
import com.nexos.inventory.enums.Errors;
import com.nexos.inventory.exceptions.CustomException;
import com.nexos.inventory.model.Product;
import com.nexos.inventory.model.User;
import com.nexos.inventory.service.ProductService;
import com.nexos.inventory.service.UserService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController()
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping()
    private ResponseEntity<Page<ProductDTO>> getProducts(@QuerydslPredicate(root = Product.class) Predicate predicate,
                                                         Pageable pageable) {
        return ResponseEntity.ok(
                productService.findAll(predicate, pageable).map(ProductDTO.TO_DTO)
        );
    }

    @PostMapping()
    private ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto, @RequestParam Long userId) {
        validateProductName(dto.getName());
        Product product = ProductDTO.TO_ENTITY.apply(dto);
        User user = userService.findOne(userId);
        product.setId(null);
        product.setCreateUser(user);
        product.setUpdateUser(user);
        productService.save(product);
        return ResponseEntity.ok(ProductDTO.from(product));
    }

    @PutMapping("/{id}")
    private ResponseEntity<ProductDTO> update(@RequestBody ProductDTO dto, @PathVariable Long id, @RequestParam Long userId) {
        productService.findOne(id);
        User user = userService.findOne(userId);
        validateProductName(dto.getName());
        Product product = ProductDTO.TO_ENTITY.apply(dto);
        product.setUpdateUser(user);
        productService.save(product);
        return ResponseEntity.ok(
                ProductDTO.TO_DTO.apply(product)
        );
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ProductDTO> delete(@PathVariable Long id, @RequestParam Long userId) {
        Product product = productService.findOne(id);
        userService.findOne(userId);

        if (!Objects.equals(product.getCreateUser().getId(), userId))
            throw new CustomException(HttpStatus.BAD_REQUEST, Errors.CANT_DELETE_PRODUCT_USER_DISMATCH);

        productService.delete(product);
        return ResponseEntity.ok(
                ProductDTO.TO_DTO.apply(product)
        );
    }

    private void validateProductName(String name) throws CustomException {
        if (productService.existsByNameEqualsIgnoreCase(name))
            throw new CustomException(HttpStatus.BAD_REQUEST, Errors.PRODUCT_NAME_ALREADY_REGISTERED);
    }

}
