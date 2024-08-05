package org.hanumoka.sample.board.infra;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.account.infrastructure.jpa.AccountEntity;
import org.hibernate.annotations.Comment;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    @Comment("작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AccountEntity author;
}
