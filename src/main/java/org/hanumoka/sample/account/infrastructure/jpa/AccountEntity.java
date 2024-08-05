package org.hanumoka.sample.account.infrastructure.jpa;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.domain.Account;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.hanumoka.sample.common.domain.vo.Email;

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

    @Setter
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<BoardEntity> boards;

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
