package org.hanumoka.sample.board.graphql.dto;

import lombok.*;
import org.hanumoka.sample.board.infra.BoardEntity;


@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardEdge {
    private BoardEntity node;
    private String cursor;
}
