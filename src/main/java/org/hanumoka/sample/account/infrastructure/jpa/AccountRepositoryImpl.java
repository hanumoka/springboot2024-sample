package org.hanumoka.sample.account.infrastructure.jpa;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepo accountJpaRepo;

    @Override
    public Optional<Account> findByUsername(String username) {
        return accountJpaRepo.findByUsername(username).map(AccountEntity::toDomain);
    }

    @Override
    public Account save(Account account) {
        return accountJpaRepo.save(AccountEntity.fromDomain(account)).toDomain();
    }

    @Override
    public Optional<Account> findById(Long memberId) {
        return accountJpaRepo.findById(memberId).map(AccountEntity::toDomain);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountJpaRepo.findAll(pageable).map(AccountEntity::toDomain);
    }

    @Override
    public void delete(Account account) {
        accountJpaRepo.delete(AccountEntity.fromDomain(account));
    }
}
