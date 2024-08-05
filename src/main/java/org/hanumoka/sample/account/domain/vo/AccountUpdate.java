package org.hanumoka.sample.account.domain.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AccountUpdate {
    private Long id;
    private String username;
    private String name;

    @Builder
    public AccountUpdate(Long id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
}
