package org.hanumoka.sample.account.presentation.rest.response;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.account.domain.Account;

@Builder
@Getter
public class AccountResponseDto {
    private long id;
    private String username;
    private String name;

    public static AccountResponseDto from(Account account){
        return AccountResponseDto.builder()
                .id(account.getId())
                .username(account.getUsername().toString())
                .name(account.getName())
                .build();
    }
}
