package org.hanumoka.sample.either;

/**
 * 클래스 설명란
 *
 * @author       : KYB
 * @since        : 24. 10. 23.
 */

sealed interface OrderError {
    record NotFound(String message) implements OrderError {}
    record InvalidState(String message) implements OrderError {}
}