package org.hanumoka.sample.account.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.domain.vo.AccountCreate;
import org.hanumoka.sample.account.presentation.rest.request.CreateAccountRequest;
import org.hanumoka.sample.account.application.service.AccountCommandServiceImpl;
import org.hanumoka.sample.account.application.service.AccountQueryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/account/command")
@RestController
public class AccountCommandController {

    private final AccountQueryServiceImpl memberQueryServiceImpl;
    private final AccountCommandServiceImpl accountCommandServiceImpl;
    
    //1. account 생성
    @PostMapping("/create")
    public ResponseEntity<Long> createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        Long accountId = accountCommandServiceImpl.createAccount(createAccountRequest.toDomain());
        return ResponseEntity.ok(accountId);
    }

    //2. account 수정
    @PostMapping("/update")
    public ResponseEntity<Long> updateAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(1l);
    }

    //3. account 삭제
    @PostMapping("/delete")
    public ResponseEntity<Long> deleteAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return ResponseEntity.ok(1l);
    }

}
