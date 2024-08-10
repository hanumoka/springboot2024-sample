package org.hanumoka.sample.account.presentation.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.application.port.in.AccountQueryService;
import org.hanumoka.sample.account.application.service.AccountQueryServiceImpl;
import org.hanumoka.sample.account.presentation.rest.request.PageAccountRequestDto;
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



    @GetMapping("/get-page")
    public ResponseEntity<Page<AccountResponseDto>> getPage(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @ModelAttribute PageAccountRequestDto pageAccountRequestDto
    ) {
        System.out.println("pageable = " + pageable);
        accountQueryService.getPage(pageable, pageAccountRequestDto);
        return null;
    }

}
