package org.hanumoka.sample.account.domain.vo;

import lombok.Builder;

public class AccountCreate {
    private String username;
    private String name;

    @Builder
    public AccountCreate(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }
}
