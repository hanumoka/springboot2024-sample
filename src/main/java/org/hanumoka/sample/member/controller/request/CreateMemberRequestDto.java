package org.hanumoka.sample.member.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class CreateMemberRequestDto {
    private String username;
    private String name;
}
