package org.hanumoka.sample.member.infrastructure.jpa;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.hanumoka.sample.member.domain.Member;

import java.util.List;

@Builder
@AllArgsConstructor
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "member")
public class MemberEntity {
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

    public static MemberEntity fromDomain(Member member) {
        return MemberEntity.builder()
                .id(member.getId())
                .username(member.getUsername())
                .name(member.getName())
                .build();
    }

    //TODO: ???? infra 레이어가 도메인 레이어를 참조하는것은 옳은가? 괜찮다 도메인이 인프라 레이어를 모르는게 좋다.
    public Member toDomain() {
        return Member.builder()
                .id(id)
                .username(username)
                .name(name)
                .build();
    }
}
