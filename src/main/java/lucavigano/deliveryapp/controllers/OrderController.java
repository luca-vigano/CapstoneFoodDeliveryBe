package lucavigano.deliveryapp.controllers;


import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.DTO.PaymentResponse;
import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.OrderService;
import lucavigano.deliveryapp.service.PaymentService;
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
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse createOrder(@RequestBody OrderRequest req,
                                       @AuthenticationPrincipal User currentUser) throws Exception {
        Order order = orderService.createOrder(req, currentUser);
        PaymentResponse response=paymentService.createPaymentLink(order);
        return response;
    }

    @GetMapping("/order/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderHistory(@AuthenticationPrincipal User currentUser) throws Exception {
        List<Order> orders = orderService.getUsersOrder(currentUser.getId());
        return orders;
    }
}

