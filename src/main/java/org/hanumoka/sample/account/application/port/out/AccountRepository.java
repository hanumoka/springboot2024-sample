package org.hanumoka.sample.account.application.port.out;

import org.hanumoka.sample.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findById(Long accountId);

    Optional<Account> findByUsername(String username);

    Collection<Account> findAll();

    Page<Account> getPage(Pageable pageable);

    Account save(Account account);

    //Account는 실제 삭제되는 것이 아니라 상태만 변경되므로 삭제 메서드는 필요하지 않음
//    void delete(Long accountId);
}
