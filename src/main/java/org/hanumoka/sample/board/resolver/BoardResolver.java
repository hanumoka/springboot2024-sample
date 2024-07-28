package org.hanumoka.sample.board.resolver;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.controller.BoardDTO;
import org.hanumoka.sample.board.service.BoardService;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Board 그래프 QL Resolver = Controller
 */

@RequiredArgsConstructor
@Controller
public class BoardResolver {

    private final BoardService boardService;

    @QueryMapping
    public List<Board> boards() {
        List<BoardDTO> allBoard = boardService.getAllBoard();

        System.out.println("allBoard = " + allBoard);

        return allBoard.stream()
                .map(boardDTO -> Board.builder()
                        .id(boardDTO.getId())
                        .title(boardDTO.getTitle())
                        .content(boardDTO.getContent())
                        .build())
                .collect(Collectors.toList());
    }

}
