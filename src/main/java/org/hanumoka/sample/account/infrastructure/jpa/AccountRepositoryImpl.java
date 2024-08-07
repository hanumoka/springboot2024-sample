package org.hanumoka.sample.account.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository accountJpaRepository;

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountJpaRepository.findByUsername(username).map(AccountEntity::toDomain);
    }

    @Override
    public Collection<Account> findAll() {
        return List.of();
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepository.save(AccountEntity.fromDomain(account)).toDomain();
    }

    @Override
    public Optional<Account> findById(Long memberId) {
        return accountJpaRepository.findById(memberId).map(AccountEntity::toDomain);
    }

    @Override
    public Page<Account> getPage(Pageable pageable) {
        return accountJpaRepository.findAll(pageable).map(AccountEntity::toDomain);
    }

}
