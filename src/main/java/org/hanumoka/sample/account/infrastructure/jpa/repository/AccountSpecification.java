package org.hanumoka.sample.account.infrastructure.jpa.repository;

import jakarta.persistence.criteria.Predicate;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.hanumoka.sample.account.presentation.rest.request.QueryAccountRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {
    public static Specification<AccountEntity> withDynamicQuery(QueryAccountRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getId()));
            }

            if (request.getUsername() != null && !request.getUsername().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("username"), "%" + request.getUsername() + "%"));
            }

            if (request.getAccountUuid() != null && !request.getAccountUuid().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("accountUuid"), request.getAccountUuid()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}