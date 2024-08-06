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

    //단건 계정 조회

    //전체 계정 조회

    //페이징 처리된 계정 조회 //TODO: common 도메인이 모든 도메인이서 공통적으로 사용할수 있는 페이지네이션 인터페이스를 만들자
}
