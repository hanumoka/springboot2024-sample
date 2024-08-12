package org.hanumoka.sample.account.infrastructure.jpa.repository;

import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRoleJpaRepository extends JpaRepository<AccountRoleEntity, Long> {
    List<AccountRoleEntity> findByAccountEntity(AccountEntity accountEntity);
}
