package org.hanumoka.sample.board.graphql;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hanumoka.sample.board.graphql.dto.BoardConnection;
import org.hanumoka.sample.board.graphql.dto.BoardEdge;
import org.hanumoka.sample.board.graphql.dto.PageInfo;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardGQController {

    private final BoardResolver boardResolver;

    /**
     * 메소드 이름이 중요하다.
     * 기본설정은 스키마와 메소드명이 동일 해야 한다. (boards)
     * @return
     */
    @QueryMapping
    public List<BoardEntity> getBoards() {
        log.info("allBoards");
        return boardResolver.getBoards();
    }

    @QueryMapping
    public BoardConnection getBoardsConnection(@Argument Integer first, @Argument String after) {
        log.info("Fetching boards connection with first: {}, after: {}", first, after);
        return boardResolver.getBoardsConnection(first, after);
//        return BoardConnection.builder().pageInfo(PageInfo.builder()
//                        .hasNextPage(true)
//                        .endCursor("test")
//                        .build())
//                .edge(Arrays.asList(BoardEdge.builder().cursor("test").build()))
//                .build();
    }

}
