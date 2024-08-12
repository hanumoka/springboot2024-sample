package org.hanumoka.sample.account.application.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountCommandService;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.domain.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Builder
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountRepository accountRepository;

    @Transactional
    @Override
    public Long createAccount(Account domain) {
        return accountRepository.createAccount(domain);
    }

    @Override
    public Long updateAccount(Account domain) {
        return accountRepository.updateAccount(domain);
    }

    @Override
    public Long deleteAccount(Account domain) {
        return accountRepository.deleteAccount(domain);
    }
}
