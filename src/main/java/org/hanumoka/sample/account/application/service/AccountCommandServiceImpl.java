package org.hanumoka.sample.account.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountCommandService;
import org.hanumoka.sample.account.application.port.out.AccountRepository;
import org.hanumoka.sample.account.application.port.out.specification.AccountCommandSpecification;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountCommandServiceImpl implements AccountCommandService {

    private final AccountRepository accountRepository;
    private final AccountCommandSpecification accountCommandSpecification;

}
