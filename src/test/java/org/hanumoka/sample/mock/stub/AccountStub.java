package org.hanumoka.sample.mock.stub;

import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;
import org.hanumoka.sample.mock.fake.FakeUuidHolder;

import java.util.HashSet;
import java.util.Set;

public class AccountStub {

    public static Account createAccount() {
        Email email = new Email("test@example.com");
        String name = "John Doe";
        Integer age = 30;
        GenderType gender = GenderType.MALE;
        AccountStatus status = AccountStatus.PENDING;
        Set<AccountRoleType> roleTypes = new HashSet<>();
        roleTypes.add(AccountRoleType.USER);

        // When
        Account account = Account.createNew(email, name, new FakeUuidHolder("aaaaaa-aaaaaa").random(), age, gender, roleTypes);

        return account;
    }
}
