package org.hanumoka.sample.account.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.application.port.in.AccountQueryService;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;
    public Page<Account> getMemberAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }
}
