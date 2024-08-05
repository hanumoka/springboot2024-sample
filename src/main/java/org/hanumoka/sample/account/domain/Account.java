package org.hanumoka.sample.account.domain;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.common.domain.BaseDomain;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * root aggregate
 */
@Getter
public class Account extends BaseDomain {
    private final Long id;
    private final Email username;
    private String name;
    private Integer age;
    private GenderType gender;
    private AccountStatus status;
    private Set<AccountRole> roles;

    @Builder
    private Account(Long id, Email username, String name, Integer age, GenderType gender, AccountStatus status, Set<AccountRole> roles) {
        super(); // BaseDomain의 생성자 호출
        validateAccountFields(username, name, status);
        this.id = id;
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.roles = new HashSet<>(roles);
    }

    public static Account createNew(Email username, String name, Integer age, GenderType gender, Set<AccountRoleType> roleTypes) {
        validateNewAccountFields(username, name, roleTypes);
        Set<AccountRole> roles = createRoles(roleTypes);
        return new Account(null, username, name, age, gender, AccountStatus.PENDING, roles);
    }

    public static Account reconstitute(Long id, Email username, String name, Integer age, GenderType gender, AccountStatus status, Set<AccountRole> roles) {
        return new Account(id, username, name, age, gender, status, roles);
    }

    public void addRole(AccountRole role) {
        roles.add(role);
        updateLastModifiedTime();
    }

    public void removeRole(AccountRole role) {
        if (roles.size() <= 1) {
            throw new IllegalStateException("Account must have at least one role");
        }
        roles.remove(role);
        updateLastModifiedTime();
    }

    public void changeStatus(AccountStatus newStatus) {
        this.status = newStatus;
        updateLastModifiedTime();
    }

    public void updateProfile(String newName, Integer newAge, GenderType newGender) {
        this.name = newName;
        this.age = newAge;
        this.gender = newGender;
        updateLastModifiedTime();
    }

    private static void validateAccountFields(Email username, String name, AccountStatus status) {
        if (username == null) {
            throw new IllegalArgumentException("Account must have a username");
        }
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Account must have a name");
        }
        if (status == null) {
            throw new IllegalArgumentException("Account must have a status");
        }
    }

    private static void validateNewAccountFields(Email username, String name, Set<AccountRoleType> roleTypes) {
        validateAccountFields(username, name, AccountStatus.PENDING);
        if (roleTypes == null || roleTypes.isEmpty()) {
            throw new IllegalArgumentException("Account must have at least one role");
        }
    }

    private static Set<AccountRole> createRoles(Set<AccountRoleType> roleTypes) {
        return roleTypes.stream()
                .map(roleType -> AccountRole.createNew(roleType, 1))
                .collect(Collectors.toSet());
    }
}