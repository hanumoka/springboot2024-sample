package org.hanumoka.sample.account.infrastructure.jpa.repository;

import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleJpaRepository extends JpaRepository<AccountRoleEntity, Long> {
}
