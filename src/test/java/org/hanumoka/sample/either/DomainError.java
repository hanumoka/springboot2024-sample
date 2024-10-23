package org.hanumoka.sample.either;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 클래스 설명란
 *
 * @author : KYB
 * @since : 24. 10. 23.
 */
// 1. 기본적인 공통 오류 타입
sealed interface DomainError {
    // 공통적으로 발생할 수 있는 기본 오류들
    record NotFound(String domain, String id, String message) implements DomainError {}
    record ValidationError(String domain, List<String> errors) implements DomainError {}
    record AuthorizationError(String message) implements DomainError {}
    record SystemError(String code, String message) implements DomainError {}

    // 도메인별 특수 오류를 위한 확장 포인트
    sealed interface OrderError extends DomainError {
        record OrderCancelled(String orderId, LocalDateTime cancelledAt)
                implements OrderError {}
        record InsufficientStock(String productId, int requested, int available)
                implements OrderError {}
    }

    sealed interface PaymentError extends DomainError {
        record PaymentDeclined(String reason) implements PaymentError {}
        record InvalidCardInfo(String detail) implements PaymentError {}
    }
}