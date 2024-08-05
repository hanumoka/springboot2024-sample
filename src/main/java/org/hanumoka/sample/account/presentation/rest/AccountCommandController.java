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
@RequestMapping("/api/account")
@RestController
public class AccountCommandController {

    private final AccountQueryServiceImpl memberQueryServiceImpl;
    private final AccountCommandServiceImpl accountCommandServiceImpl;
    
    //멤버 생성
    @PostMapping
    public ResponseEntity<Long> createMember(@RequestBody CreateAccountRequest request) {
//        AccountCreate accountCreate = AccountCreate.builder()
//                .username(request.getUsername())
//                .name(request.getName())
//                .build();
//        Long memberId = accountCommandServiceImpl.createMember(accountCreate);
//        return ResponseEntity.ok(memberId);
        return null;
    }

    //멤버 삭제
    @PostMapping("/{memberId}/delete")
    public ResponseEntity<Long> deleteMember(@PathVariable Long memberId) {
//        long deletedMemberId = accountCommandServiceImpl.deleteMember(memberId);
//        return ResponseEntity.ok(deletedMemberId);
        return null;
    }
    
}
