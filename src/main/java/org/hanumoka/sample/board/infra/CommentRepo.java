package org.hanumoka.sample.board.infra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByBoardId(Long boardId);
}
