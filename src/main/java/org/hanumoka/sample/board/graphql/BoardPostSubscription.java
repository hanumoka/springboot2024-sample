package org.hanumoka.sample.board.graphql;

import org.hanumoka.sample.board.infra.BoardEntity;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Sinks;

@Controller
public class BoardPostSubscription {

    private final Sinks.Many<BoardEntity> boardPostSink = Sinks.many().multicast().onBackpressureBuffer();

    @SubscriptionMapping
    public Publisher<BoardEntity> newBoardPost() {
        return boardPostSink.asFlux();
    }

    public void emitBoardPost(BoardEntity boardPost) {
        boardPostSink.tryEmitNext(boardPost);
    }
}
