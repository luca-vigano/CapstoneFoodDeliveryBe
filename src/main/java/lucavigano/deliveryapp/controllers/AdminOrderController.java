package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import lucavigano.deliveryapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/admin")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserServ userService;



    @GetMapping("/order/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getOrderHistory(@PathVariable Long id,
                                       @RequestParam(required = false) String order_status,
                                       @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();

        User user=userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantsOrder(id,order_status);

        return orders;
    }

    @PutMapping("/order/{orderId}/{orderStatus}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrderStatus(@PathVariable Long id,
                                       @PathVariable String orderStatus,
                                       @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();

        User user=userService.findUserByJwtToken(jwt);
        Order orders = orderService.updateOrder(id, orderStatus);

        return orders;
    }
}
