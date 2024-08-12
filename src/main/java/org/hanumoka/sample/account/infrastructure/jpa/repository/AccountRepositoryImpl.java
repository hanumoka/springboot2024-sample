package org.hanumoka.sample.account.infrastructure.jpa.repository;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.domain.AccountRole;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountEntity;
import org.hanumoka.sample.account.infrastructure.jpa.entity.AccountRoleEntity;
import org.hanumoka.sample.account.presentation.rest.request.QueryAccountRequest;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

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
    public Long updateAccount(Account domain) {
        AccountEntity savedAccountEntity = accountJpaRepository.findById(domain.getId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Account savedAccount = savedAccountEntity.toDomain();
        savedAccount.update(domain);

        AccountEntity updatedAccountEntity = accountJpaRepository.save(AccountEntity.fromDomain(savedAccount));
        updateAccountRoles(updatedAccountEntity, domain.getRoles().stream().map(AccountRole::getRoleType).collect(Collectors.toSet()));

        return updatedAccountEntity.getId();
    }

    private void updateAccountRoles(AccountEntity accountEntity, Set<AccountRoleType> newRoles) {
        Set<AccountRoleEntity> currentRoles = new HashSet<>(accountRoleJpaRepository.findByAccountEntity(accountEntity));
        Set<AccountRoleEntity> updatedRoles = newRoles.stream()
                .map(role -> AccountRoleEntity.createNew(accountEntity, AccountRole.createNew(role)))
                .collect(Collectors.toSet());

        Set<AccountRoleEntity> rolesToDelete = getRolesToDelete(currentRoles, updatedRoles);
        Set<AccountRoleEntity> rolesToAdd = getRolesToAdd(currentRoles, updatedRoles);

        accountRoleJpaRepository.deleteAll(rolesToDelete);
        accountRoleJpaRepository.saveAll(rolesToAdd);
    }

    @Override
    public Long deleteAccount(Account domain) {
        return 0L;
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


    private Set<AccountRoleEntity> getRolesToDelete(Set<AccountRoleEntity> currentRoles, Set<AccountRoleEntity> updatedRoles) {
        return currentRoles.stream()
                .filter(role -> updatedRoles.stream()
                        .noneMatch(updatedRole -> updatedRole.getRoleType().equals(role.getRoleType())))
                .collect(Collectors.toSet());
    }

    private Set<AccountRoleEntity> getRolesToAdd(Set<AccountRoleEntity> currentRoles, Set<AccountRoleEntity> updatedRoles) {
        return updatedRoles.stream()
                .filter(role -> currentRoles.stream()
                        .noneMatch(currentRole -> currentRole.getRoleType().equals(role.getRoleType())))
                .collect(Collectors.toSet());
    }

}
