package org.hanumoka.sample.account.infrastructure.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleJpaRepository extends JpaRepository<AccountRoleEntity, Long> {
}
