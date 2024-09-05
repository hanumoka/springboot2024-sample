package org.hanumoka.sample.account.domain;

import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;
import org.hanumoka.sample.mock.stub.AccountStub;
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
        Account account = Account.createNew(email, name, age, gender);

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getId()).isNull();
        assertThat(account.getRoles()).hasSize(1);
        assertThat(account.getStatus()==AccountStatus.PENDING);
        assertThat(account.getUsername()).isEqualTo(email);
        assertThat(account.getName()).isEqualTo(name);
        assertThat(account.getAge()).isEqualTo(age);
        assertThat(account.getGender()).isEqualTo(GenderType.MALE);
//        assertThat(account.getAccountUuid()).isEqualTo("aaaaaa-aaaaaa");
    }

    //Account 상태 변경 테스트
    @Test
    @DisplayName("Account 수정 - 비활성화 계정을 활성화 계정으로 변경")
    void updateActivateAccount() {
        // Given
        Account account = AccountStub.createAccount();

        // THEN
        assertThat(account.getStatus()==AccountStatus.PENDING);

        // When
        account.activate();

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getRoles()).hasSize(1);
        assertThat(account.getStatus()==AccountStatus.ACTIVE);
    }

    //Account 상태 변경 테스트
    @Test
    @DisplayName("Account 수정 - 비활성화 계정을 삭제 계정으로 변경")
    void updateDeleteAccount() {
        // Given
        Account account = AccountStub.createAccount();

        // When
        account.delete();

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getRoles()).hasSize(1);
        assertThat(account.getStatus()==AccountStatus.DELETED);
    }

    //Account 에 Role 추가 테스트
    @Test
    @DisplayName("Account 수정 - Role 추가")
    void addRole() {
        // Given
        Account account = AccountStub.createAccount();
        AccountRole role = AccountRole.createNew(AccountRoleType.ADMIN);

        // When
        account.addRole(role);

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getRoles()).hasSize(2);
    }

    //Account 에 Role 제거 테스트
    @Test
    @DisplayName("Account 수정 - Role 제거")
    void removeRole() {
        // Given
        Account account = AccountStub.createAccount();
        AccountRole role2 = AccountRole.createNew(AccountRoleType.ADMIN);
        account.addRole(role2);

        // When
        account.removeRole(role2);

        // Then
        assertThat(account).isNotNull();
        assertThat(account.getRoles()).hasSize(1);
    }

    //Account 에 Role 제거시 기존 Role 우선순위 재정렬 테스트
    @Test
    @DisplayName("Account 수정 - Role 제거시 기존 Role 우선순위 재정렬")
    void removeRoleAndReorder() {
        // Given
        Account account = AccountStub.createAccount();
        AccountRole role2 = AccountRole.createNew(AccountRoleType.ADMIN);
        AccountRole role3 = AccountRole.createNew(AccountRoleType.GUEST);
        account.addRole(role2);
        account.addRole(role3);

        // When
        account.removeRole(role2);

        // Then
        assertThat(account.getRoles()).hasSize(2);
        assertThat(account.getRoles())
                .extracting("roleType")
                .containsExactlyInAnyOrder(AccountRoleType.USER, AccountRoleType.GUEST);
    }
}
