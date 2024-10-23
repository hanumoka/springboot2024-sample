package org.hanumoka.sample.either;

/**
 * 클래스 설명란
 *
 * @author       : KYB
 * @since        : 24. 10. 23.
 */

class OrderService {

//    public Either<DomainError, OrderDTO> findOrder(String orderId) {
//        return switch(findOrderFromRepository(orderId)) {
//            case null -> new Either.Left<>(
//                    new DomainError.NotFound("Order", orderId, "주문을 찾을 수 없습니다")
//            );
//            case var order when order.isCancelled() -> new Either.Left<>(
//                    new DomainError.OrderError.OrderCancelled(
//                            orderId,
//                            order.getCancelledAt()
//                    )
//            );
//            case var order -> new Either.Right<>(OrderDTO.from(order));
//        };
//    }

    public Either<OrderError, OrderDTO> findOrder(String orderId) {
        // 주문이 없는 경우
        if (orderId.equals("not_found")) {
            return new Either.Left<>(
                    new OrderError.NotFound("주문을 찾을 수 없습니다")
            );
        }

        // 잘못된 상태인 경우
        if (orderId.equals("invalid")) {
            return new Either.Left<>(
                    new OrderError.InvalidState("유효하지 않은 주문입니다")
            );
        }

        // 정상 케이스
        return new Either.Right<>(
                new OrderDTO(orderId, "CONFIRMED", 1000)
        );
    }
}