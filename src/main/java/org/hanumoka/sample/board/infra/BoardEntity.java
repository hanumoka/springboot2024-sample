package org.hanumoka.sample.board.infra;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.comment.infra.CommentEntity;
import org.hanumoka.sample.member.infra.MemberEntity;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "board")
@Entity
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "content")
    private String content;

    @Comment("작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private MemberEntity author;

    @Comment("댓글")
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;
}
