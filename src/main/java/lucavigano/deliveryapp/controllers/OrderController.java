package lucavigano.deliveryapp.controllers;


import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServ userService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@RequestBody OrderRequest req,
                                  @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();

        User user=userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(req,user);

        return order;
    }

    @GetMapping("/order/user")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderHistory(@RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();

        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());

        return orders;
    }
}
