package org.hanumoka.sample.board.graphql;

import org.hanumoka.sample.board.infra.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardGQRepository extends JpaRepository<BoardEntity, Long> {
}
