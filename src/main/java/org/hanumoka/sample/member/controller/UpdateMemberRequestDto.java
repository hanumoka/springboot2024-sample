package org.hanumoka.sample.member.controller;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateMemberRequestDto {
    private String name;
}
