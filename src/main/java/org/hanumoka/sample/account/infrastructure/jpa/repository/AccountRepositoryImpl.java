package org.hanumoka.sample.account.infrastructure.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.domain.AccountRole;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountRoleEntity;
import org.hanumoka.sample.account.presentation.rest.request.QueryAccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;
    private final AccountRoleJpaRepository accountRoleJpaRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username).map(AccountEntity::toDomain);
    }

    @Override
    public Optional<Account> findByAccountUuid(String userUuid) {
        return accountJpaRepository.findByAccountUuid(userUuid).map(AccountEntity::toDomain);
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(AccountEntity.fromDomain(account)).toDomain();
    }

    @Override
    public Long createAccount(Account domain) {

        AccountEntity accountEntity = AccountEntity.fromDomain(domain);
        accountEntity = accountJpaRepository.save(accountEntity);

        AccountEntity finalAccountEntity = accountEntity;
        List<AccountRoleEntity> accountRoleEntities = domain.getRoles().stream().map(role -> AccountRoleEntity.createNew(finalAccountEntity, role)).toList();
        accountRoleJpaRepository.saveAll(accountRoleEntities);
        return accountEntity.getId();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountJpaRepository.findById(id).map(AccountEntity::toDomain);
    }

    @Override
    public Page<Account> getPage(Pageable pageable, QueryAccountRequest queryAccountRequest) {
        Specification<AccountEntity> spec = AccountSpecification.withDynamicQuery(queryAccountRequest);
        return accountJpaRepository.findAll(spec, pageable).map(AccountEntity::toDomain);
    }

}
