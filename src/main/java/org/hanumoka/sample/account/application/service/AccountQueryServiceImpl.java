package org.hanumoka.sample.account.application.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountQueryService;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.springframework.stereotype.Service;

@Builder
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountQueryServiceImpl implements AccountQueryService {

    private final AccountRepository accountRepository;

}
