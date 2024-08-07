package org.hanumoka.sample.board.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepo extends JpaRepository<BoardEntity, Long> {
    Optional<BoardEntity> findByIdAndAuthorId(Long id, Long authorId);
    List<BoardEntity> findAllByAuthorId(Long authorId);
}
