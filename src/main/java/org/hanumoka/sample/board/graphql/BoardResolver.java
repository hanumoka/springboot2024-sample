package org.hanumoka.sample.board.graphql;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardResolver {

    private final BoardGQRepository boardGQRepository;

    @QueryMapping
    public List<BoardEntity> getBoards() {
        return boardGQRepository.findAll();
    }

}
