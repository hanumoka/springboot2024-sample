package org.hanumoka.sample.board.controller.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateBoardRequestDto {
    private String title;
    private String content;
}
