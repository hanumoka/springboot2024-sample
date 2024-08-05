package org.hanumoka.sample.account.domain;

import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {


    @Test
    @DisplayName("Account 생성 - 유효한 역할로 생성")
    void createNewAccountWithValidRole() {
        // Given
        Email email = new Email("test@example.com");
        String name = "John Doe";
        Integer age = 30;
        GenderType gender = GenderType.MALE;
        AccountStatus status = AccountStatus.PENDING;
        Set<AccountRoleType> roleTypes = new HashSet<>();
        roleTypes.add(AccountRoleType.USER);

        // When
        Account account = Account.createNew(email, name, age, gender, roleTypes);

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getRoles()).hasSize(1);
        assertThat(account.getStatus()==AccountStatus.PENDING);
        assertThat(account.getUsername()).isEqualTo(email);
        assertThat(account.getName()).isEqualTo(name);
        assertThat(account.getAge()).isEqualTo(age);
    }
    
    //단건 Account 조회 테스트

    //단건 Accuont 상태 변경 테스트

    //Account 삭제 테스트

    //Account 전체 조회 테스트


}
