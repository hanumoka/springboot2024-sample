package org.hanumoka.sample.board.graphql;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.graphql.dto.BoardConnection;
import org.hanumoka.sample.board.graphql.dto.BoardEdge;
import org.hanumoka.sample.board.graphql.dto.PageInfo;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardResolver {

    private final BoardGQRepository boardGQRepository;

    public List<BoardEntity> getBoards() {
        return boardGQRepository.findAll();
    }

    public BoardConnection getBoardsConnection(@Argument Integer first, @Argument String after) {
        int page = 0;
        if (after != null) {
            page = decodeCursor(after);
        }

        Pageable pageable = PageRequest.of(page, first != null ? first : 10);
        Page<BoardEntity> boardPage = boardGQRepository.findAll(pageable);

        List<BoardEdge> edges = boardPage.getContent().stream()
                .map(board -> new BoardEdge(board, encodeCursor(board.getId())))
                .collect(Collectors.toList());

        PageInfo pageInfo = new PageInfo(boardPage.hasNext(),
                edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor());

        return new BoardConnection(edges, pageInfo);
    }

    private String encodeCursor(Long id) {
        return Base64.getEncoder().encodeToString(id.toString().getBytes());
    }

    private int decodeCursor(String cursor) {
        String decoded = new String(Base64.getDecoder().decode(cursor));
        return Integer.parseInt(decoded) / 10; // Assuming 10 items per page
    }

}
