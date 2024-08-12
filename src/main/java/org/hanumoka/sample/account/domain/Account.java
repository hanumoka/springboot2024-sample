package org.hanumoka.sample.account.domain;

import lombok.Builder;
import lombok.Getter;
import org.hanumoka.sample.common.type.AccountRoleType;
import org.hanumoka.sample.common.type.GenderType;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * root aggregate
 */
@Getter
public class Account {

    private final Long id;
    private final Email username;
    private final String accountUuid;
    private String name;
    private Integer age;
    private GenderType gender;
    private AccountStatus status;
    private Set<AccountRole> roles;

    @Builder
    private Account(Long id, Email username, String accountUuid, String name, Integer age, GenderType gender, AccountStatus status, Set<AccountRole> roles) {
//        validateAccountFields(username, name, status, roles);
        this.id = id;
        this.username = username;
        this.accountUuid = accountUuid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.roles = new HashSet<>(roles);
    }

    public static Account createNew(
            Email username
            , String name
            , Integer age
            , GenderType gender
    ) {
        validateCreateAccountFields(username, name, AccountStatus.PENDING);
        Set<AccountRoleType> roleTypes = Set.of(AccountRoleType.USER);
        String accountUuid = UUID.randomUUID().toString();
        Set<AccountRole> roles = createRoles(roleTypes);
        return new Account(null, username, accountUuid, name, age, gender, AccountStatus.PENDING, roles);
    }

    public static Account reconstitute(Long id, Email username, String accountUuid, String name, Integer age, GenderType gender, AccountStatus status, Set<AccountRole> roles) {
        return new Account(id, username, accountUuid, name, age, gender, status, roles);
    }

    public void changeStatus(AccountStatus newStatus) {
        this.status = newStatus;
    }

    public void updateProfile(String newName, Integer newAge, GenderType newGender) {
        this.name = newName;
        this.age = newAge;
        this.gender = newGender;
    }

    private static void validateCreateAccountFields(Email username, String name, AccountStatus status) {
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

    public void activate() {
        status = AccountStatus.ACTIVE;
    }

    public void delete() {
        status = AccountStatus.DELETED;
    }

    private static Set<AccountRole> createRoles(Set<AccountRoleType> roleTypes) {
        return roleTypes.stream()
                .map(AccountRole::createNew)
                .collect(Collectors.toSet());
    }

    public void addRole(AccountRole role) {
        roles.add(role);
    }

    public void removeRole(AccountRole roleToRemove) {
        if (roles.size() <= 1) {
            throw new IllegalStateException("Account must have at least one role");
        }

        if (!roles.remove(roleToRemove)) {
            throw new IllegalArgumentException("Role not found in this account");
        }
    }

    public void update(Account domain) {
        this.name = domain.getName();
        this.age = domain.getAge();
        this.gender = domain.getGender();
        this.status = domain.getStatus();}
}