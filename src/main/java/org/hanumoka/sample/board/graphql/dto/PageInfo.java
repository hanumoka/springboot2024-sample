package org.hanumoka.sample.board.graphql.dto;


import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PageInfo {
    private boolean hasNextPage;
    private String endCursor;
}
