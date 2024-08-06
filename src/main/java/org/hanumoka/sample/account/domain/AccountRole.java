package org.hanumoka.sample.account.domain;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.common.type.AccountRoleType;

import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final AccountRole that = (AccountRole) o;
        return this.roleType == that.roleType;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.roleType);
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
