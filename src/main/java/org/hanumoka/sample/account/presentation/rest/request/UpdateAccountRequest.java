package org.hanumoka.sample.account.presentation.rest.request;

import lombok.*;
import org.hanumoka.sample.account.domain.Account;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@Data
public class UpdateAccountRequest {
    private String name;

    public Account toDomain() {
        return Account.builder()
                .name(name)
                .build();
    }
}
