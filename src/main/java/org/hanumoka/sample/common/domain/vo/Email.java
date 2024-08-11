package org.hanumoka.sample.common.domain.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    private String email;

    @Builder
    public Email(String email) {
        validateEmailFormat(email);
        this.email = email;
    }

    public static Email from(String username) {
        return Email.builder()
                .email(username)
                .build();
    }

    public static void validateEmailFormat(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    @Override
    public String toString() {
        return email;
    }
}
