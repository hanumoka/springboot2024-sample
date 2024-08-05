package org.hanumoka.sample.account.presentation.rest.request;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateAccountRequest {
    private String name;
}
