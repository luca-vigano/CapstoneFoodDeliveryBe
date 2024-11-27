package lucavigano.deliveryapp.controllers;


import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderRequest req,
                             @AuthenticationPrincipal User currentUser) throws Exception {
        Order order = orderService.createOrder(req, currentUser);
        return order;
    }

    @GetMapping("/order/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderHistory(@AuthenticationPrincipal User currentUser) throws Exception {
        List<Order> orders = orderService.getUsersOrder(currentUser.getId());
        return orders;
    }
}

