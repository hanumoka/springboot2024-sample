package org.hanumoka.sample.account.infrastructure.jpa.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.account.domain.type.AccountStatus;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.hanumoka.sample.common.domain.vo.Email;
import org.hanumoka.sample.common.type.GenderType;

import java.util.List;

@Builder
@AllArgsConstructor
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "account_uuid")
    private String accountUuid;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

//    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
//    private List<BoardEntity> boards;

    public static AccountEntity fromDomain(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .username(account.getUsername().toString())
                .name(account.getName())
                .build();
    }

    public Account toDomain() {
        return Account.builder()
                .id(id)
                .username(Email.from(username))
                .name(name)
                .build();
    }
}
