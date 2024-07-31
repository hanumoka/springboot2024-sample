package org.hanumoka.sample.comment.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJpaRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardId(Long boardId);
}
