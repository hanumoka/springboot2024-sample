package org.hanumoka.sample.board.graphql;

import lombok.RequiredArgsConstructor;
import org.hanumoka.sample.board.infra.BoardEntity;
import org.hanumoka.sample.board.infra.BoardJpaRepo;
import org.hanumoka.sample.board.service.BoardService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BoardPostMutation {

    private final BoardPostSubscription boardPostSubscription;
    private final BoardService boardService;
    private final BoardJpaRepo boardJpaRepo;

    @MutationMapping
    public BoardEntity createBoardPost(@Argument Long authorId, @Argument String title, @Argument String content) {
        Long boardId = boardService.createBoard(authorId, title, content);

        BoardEntity boardEntity = boardJpaRepo.findById(boardId).orElse(null);

        // Subscription을 통해 새 게시글 알림
        boardPostSubscription.emitBoardPost(boardEntity);

        return boardEntity;
    }
}
