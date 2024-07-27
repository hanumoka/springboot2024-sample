package org.hanumoka.sample.board.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardId(Long boardId);
}
