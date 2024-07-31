package org.hanumoka.sample.member.infra;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.infra.BoardEntity;

import java.util.ArrayList;
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
    private List<BoardEntity> boards = new ArrayList<>();;
}
