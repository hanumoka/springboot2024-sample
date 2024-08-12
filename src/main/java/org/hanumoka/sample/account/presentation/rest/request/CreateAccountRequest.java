package org.hanumoka.sample.account.presentation.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.domain.AccountRole;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;

import java.util.Set;

@Builder
@AllArgsConstructor
@Data
public class CreateAccountRequest {
    private String username;
    private String name;
    private Integer age;
    private GenderType gender;

    public Account toDomain() {
        return Account.createNew(Email.from(username), name, age, gender);
    }
}
