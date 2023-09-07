package com.nexos.inventory.repository;

import com.nexos.inventory.model.QRole;
import com.nexos.inventory.model.Role;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> , QuerydslPredicateExecutor<Role>, QuerydslBinderCustomizer<QRole> {



    @Override
    default void customize(QuerydslBindings bindings, QRole root) {
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
        bindings.excluding(root.id);
    }

}
