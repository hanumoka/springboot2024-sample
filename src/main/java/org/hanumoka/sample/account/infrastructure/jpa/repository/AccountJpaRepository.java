package org.hanumoka.sample.account.infrastructure.jpa.repository;

import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByAccountUuid(String accountUuid);

    Page<AccountEntity> findAll(Specification<org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity> spec, Pageable pageable);
}
