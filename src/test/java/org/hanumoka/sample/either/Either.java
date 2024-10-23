package org.hanumoka.sample.either;

import java.util.function.Function;

/**
 * 클래스 설명란
 *
 * @author       : KYB
 * @since        : 24. 10. 23.
 */
public sealed interface Either<L, R> {
    record Left<L, R>(L value) implements Either<L, R> {}
    record Right<L, R>(R value) implements Either<L, R> {}

    default <T> T fold(
            Function<L, T> leftMapper,
            Function<R, T> rightMapper
    ) {
        return switch (this) {
            case Left(var value) -> leftMapper.apply(value);
            case Right(var value) -> rightMapper.apply(value);
        };
    }
}