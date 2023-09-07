package com.nexos.inventory.dtos;

import com.nexos.inventory.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.function.Function;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ProductDTO extends AuditableDTO implements Serializable {

    private Long id;
    private String name;
    private Integer quantity;
    private LocalDate entryDate;
    private UserDTO createUser;
    private UserDTO updateUser;

    public static ProductDTO from(Product product) {
        return TO_DTO.apply(product);
    }

    public static Function<ProductDTO, Product> TO_ENTITY = (ProductDTO dto) -> {
        var product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setEntryDate(dto.getEntryDate());
        product.setCreatedAt(dto.getCreatedAt());
        product.setUpdatedAt(dto.getUpdatedAt());
        return product;
    };

    public static Function<Product, ProductDTO> TO_DTO = (Product entity) -> {
        var dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setQuantity(entity.getQuantity());
        dto.setEntryDate(entity.getEntryDate());
        dto.setCreateUser(UserDTO.TO_DTO.apply(entity.getCreateUser()));
        dto.setUpdateUser(UserDTO.TO_DTO.apply(entity.getUpdateUser()));
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    };

    private static final long serialVersionUID = -3177336654137618721L;
}
