package org.hanumoka.sample.member.controller.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateMemberRequestDto {
    private String name;
}
