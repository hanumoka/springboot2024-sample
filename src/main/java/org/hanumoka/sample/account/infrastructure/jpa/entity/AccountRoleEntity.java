package org.hanumoka.sample.account.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.domain.AccountRole;
import org.hanumoka.sample.common.type.AccountRoleType;

@Builder
@AllArgsConstructor
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "account_role")
public class AccountRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AccountRoleType roleType;

    public AccountRole toDomain() {
        return AccountRole.builder()
                .id(id)
                .roleType(roleType)
                .build();
    }
}
