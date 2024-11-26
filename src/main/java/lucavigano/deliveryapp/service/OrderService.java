package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.entities.Order;
import lucavigano.deliveryapp.entities.User;

import java.util.List;

public interface OrderService {

    public Order createOrder (OrderRequest order, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userid) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById(Long orderId) throws Exception;

}
