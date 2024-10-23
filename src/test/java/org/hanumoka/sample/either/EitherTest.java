package org.hanumoka.sample.either;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 클래스 설명란
 *
 * @author : KYB
 * @since : 24. 10. 23.
 */

@Slf4j
public class EitherTest {

//    // 5. 컨트롤러에서의 사용
//    @RestController
//    class OrderController {
//        private final OrderService orderService;
//
//        @GetMapping("/orders/{orderId}")
//        public ResponseEntity<?> getOrder(@PathVariable String orderId) {
//            return orderService.findOrder(orderId)
//                    .fold(
//                            // 에러 처리
//                            error -> switch (error) {
//                                case OrderError.NotFound e ->
//                                        ResponseEntity.notFound().build();
//                                case OrderError.InvalidState e ->
//                                        ResponseEntity.badRequest().body(e.message());
//                            },
//                            // 성공 처리
//                            order -> ResponseEntity.ok(order)
//                    );
//        }
//    }

    @Test
    void findOrder_WhenOrderExists_ReturnsOrder() {
        OrderService service = new OrderService();
        var result = service.findOrder("valid_id");

        assertTrue(result instanceof Either.Right);
        result.fold(
                error -> fail("Should not return error"),
                order -> {
                    assertEquals("valid_id", order.id());
                    assertEquals("CONFIRMED", order.status());
                    return null;
                }
        );
    }

    @Test
    void findOrder_WhenOrderNotFound_ReturnsError() {
        OrderService service = new OrderService();
        var result = service.findOrder("not_found");

        assertTrue(result instanceof Either.Left);
        result.fold(
                error -> {
                    assertTrue(error instanceof OrderError.NotFound);
                    assertEquals("주문을 찾을 수 없습니다", ((OrderError.NotFound) error).message());
                    return null;
                },
                order -> fail("Should not return order")
        );
    }
}
