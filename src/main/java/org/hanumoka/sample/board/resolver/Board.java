package org.hanumoka.sample.board.resolver;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class Board {
    private long id;
    private String title;
    private String content;
//    private String testPro;
}
