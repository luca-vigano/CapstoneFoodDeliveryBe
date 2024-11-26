package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderHistory(@PathVariable Long id,
                                       @RequestParam(required = false) String order_status,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        List<Order> orders = orderService.getRestaurantsOrder(id, order_status);
        return orders;
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrderStatus(@PathVariable Long orderId,
                                   @PathVariable String orderStatus,
                                   @AuthenticationPrincipal User currentUser) throws Exception {
        Order order = orderService.updateOrder(orderId, orderStatus);
        return order;
    }
}

