package org.hanumoka.sample.account.application.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountQueryService;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.presentation.rest.request.QueryAccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Builder
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountQueryServiceImpl implements AccountQueryService {
    private final AccountRepository accountRepository;

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Account getAccountByAccountUuid(String userUuid) {
        return accountRepository.findByAccountUuid(userUuid).orElse(null);
    }

    @Override
    public Page<Account> getPage(Pageable pageable, QueryAccountRequest queryAccountRequest) {
        return accountRepository.getPage(pageable, queryAccountRequest);
    }


}
