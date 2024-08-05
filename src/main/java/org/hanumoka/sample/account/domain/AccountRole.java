package org.hanumoka.sample.account.domain;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.common.type.AccountRoleType;

@Getter
public class AccountRole {
    private Long id;
    private AccountRoleType roleType;
    private int priority;

    @Builder
    public AccountRole(Long id, AccountRoleType roleType, int priority) {
        this.id = id;
        this.roleType = roleType;
        this.priority = priority;
    }

    public static AccountRole createNew(AccountRoleType roleType, int priority) {
        return AccountRole.builder()
                .roleType(roleType)
                .priority(priority)
                .build();
    }
}
