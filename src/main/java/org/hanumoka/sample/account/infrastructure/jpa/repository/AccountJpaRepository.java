package org.hanumoka.sample.account.infrastructure.jpa.repository;

import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByUsername(String username);

    Optional<AccountEntity> findByAccountUuid(String accountUuid);
}
