package org.hanumoka.sample.account.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountQueryService;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.presentation.rest.request.QueryAccountRequest;
import org.hanumoka.sample.account.presentation.rest.response.AccountResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class AccountQueryController {
    private final AccountQueryService accountQueryService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccountById(@PathVariable Long id) {
        Account account = accountQueryService.getAccountById(id);
        AccountResponseDto accountResponseDto = AccountResponseDto.from(account);
        return ResponseEntity.ok(accountResponseDto);
    }

    @GetMapping("/get-page")
    public ResponseEntity<Page<AccountResponseDto>> getPage(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @ModelAttribute QueryAccountRequest queryAccountRequest
    ) {
        Page<Account> page = accountQueryService.getPage(pageable, queryAccountRequest);
        Page<AccountResponseDto> responseDtoPage = page.map(AccountResponseDto::from);
        return ResponseEntity.ok(responseDtoPage);
    }

    @GetMapping("/query")
    public ResponseEntity<AccountResponseDto> getAccountByQueryAccountRequest(@ModelAttribute QueryAccountRequest queryAccountRequest) {

        Account account = null;
        if(queryAccountRequest.getId() != null) {
            account = accountQueryService.getAccountById(queryAccountRequest.getId());
        }else if(queryAccountRequest.getUsername() != null) {
            account = accountQueryService.getAccountByUsername(queryAccountRequest.getUsername());
        }else if(queryAccountRequest.getAccountUuid() != null) {
            account = accountQueryService.getAccountByAccountUuid(queryAccountRequest.getAccountUuid());
        }//else if

        AccountResponseDto accountResponseDto = AccountResponseDto.from(account);
        return ResponseEntity.ok(accountResponseDto);
    }

}
