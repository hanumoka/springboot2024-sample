package org.hanumoka.sample.board.graphql.dto;


import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BoardConnection {
    private List<BoardEdge> edge;
    private PageInfo pageInfo;
}
