package org.hanumoka.sample.account.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountCommandService;
import org.hanumoka.sample.account.domain.vo.AccountCreate;
import org.hanumoka.sample.account.presentation.rest.request.CreateAccountRequest;
import org.hanumoka.sample.account.application.service.AccountCommandServiceImpl;
import org.hanumoka.sample.account.application.service.AccountQueryServiceImpl;
import org.hanumoka.sample.account.presentation.rest.request.DeleteAccountRequest;
import org.hanumoka.sample.account.presentation.rest.request.UpdateAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/account/command")
@RestController
public class AccountCommandController {

    private final AccountCommandService accountCommandService;
    
    //1. account 생성
    @PostMapping("/create")
    public ResponseEntity<Long> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Long accountId = accountCommandService.createAccount(createAccountRequest.toDomain());
        return ResponseEntity.ok(accountId);
    }

    //2. account 수정
    @PostMapping("/update")
    public ResponseEntity<Long> updateAccount(@RequestBody UpdateAccountRequest updateAccountRequest) {
        Long accountId = accountCommandService.updateAccount(updateAccountRequest.toDomain());
        return ResponseEntity.ok(1l);
    }

    //3. account 삭제
    @PostMapping("/delete")
    public ResponseEntity<Long> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest) {
        Long accountId = accountCommandService.deleteAccount(deleteAccountRequest.toDomain());
        return ResponseEntity.ok(1l);
    }

}
