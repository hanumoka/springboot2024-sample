package org.hanumoka.sample.board.controller;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateBoardRequestDto {
    private String title;
    private String content;
}
