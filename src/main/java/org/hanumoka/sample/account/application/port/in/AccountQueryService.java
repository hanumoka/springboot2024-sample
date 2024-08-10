package org.hanumoka.sample.account.application.port.in;

import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.presentation.rest.request.PageAccountRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountQueryService {

    //1. 단건 조회
    Account getAccountById(Long accountId);
    Account getAccountByUsername(String username);
    Account getAccountByUserUuid(Long userUuid);

    //3. 페이징 조회
    Page<Account> getPage(Pageable pageable, PageAccountRequestDto pageAccountRequestDto);
}
