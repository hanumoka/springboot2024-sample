package org.hanumoka.sample.account.application.port.out;

import org.hanumoka.sample.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findByUsername(String username);

    Account save(Account account);

    Optional<Account> findById(Long memberId);

    Page<Account> findAll(Pageable pageable);

    void delete(Account account);
}
