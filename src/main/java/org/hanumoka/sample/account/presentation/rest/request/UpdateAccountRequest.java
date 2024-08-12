package org.hanumoka.sample.account.presentation.rest.request;

import lombok.*;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.domain.AccountRole;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateAccountRequest {
    private Long id;
    private String name;
    private Integer age;
    private GenderType gender;
    private AccountStatus status;
    private Set<AccountRoleType> roles = new HashSet<>();

    public Account toDomain() {

        Set<AccountRole> accountRoles = roles.stream()
                .map(AccountRole::createNew)
                .collect(Collectors.toSet());

        return Account.builder()
                .id(id)
                .name(name)
                .age(age)
                .gender(gender)
                .status(status)
                .roles(accountRoles)
                .build();
    }
}
