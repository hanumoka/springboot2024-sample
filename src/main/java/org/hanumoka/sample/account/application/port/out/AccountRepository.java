package org.hanumoka.sample.account.application.port.out;

import org.hanumoka.sample.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Long accountId);

    Optional<Account> findByUsername(String username);

    Page<Account> getPage(Pageable pageable);

    Account save(Account account);

}
