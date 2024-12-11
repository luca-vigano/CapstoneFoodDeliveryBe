package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.OrderRequest;
import lucavigano.deliveryapp.entities.*;
import lucavigano.deliveryapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;




    public Order createOrder(OrderRequest order, User user) throws Exception {

        Address shippingAddress= order.getDeliveryAddress();

        Address savedAddress=addressRepository.save(shippingAddress);

        if (!user.getAddress().contains(savedAddress)){
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(order.getRestaurantId());

        Order createdOrder=new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreateAt(LocalDateTime.now());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setRestaurant(restaurant);


        Cart cart=cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems=new ArrayList<>();

        for (CartItem cartItem: cart.getItems()){
            OrderItem orderItem=new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIngredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderitem=orderItemRepository.save(orderItem);
            orderItems.add(savedOrderitem);
        }

        Long totalCart = cartService.calculateCartTotal(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalCart);
        createdOrder.setTotalAmmount(totalCart + 3 + 2);
        createdOrder.setTotalItem(orderItems.size());

        Order savedOrder=orderRepository.save(createdOrder);
        restaurant.getOrders().add(savedOrder);

        return createdOrder;
    }


    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        Order order=findOrderById(orderId);

        if (orderStatus.equals("OUT_FOR_DELIVERY")
                || orderStatus.equals("DELIVERED")
                || orderStatus.equals("COMPLETED")
                || orderStatus.equals("PENDING")
        ){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Selezionare uno stato dell'ordine valido");
    }


    public void cancelOrder(Long orderId) throws Exception {

        Order order=findOrderById(orderId);
        //controllare che non sia deleteById(order.getId())
        orderRepository.deleteById(orderId);
    }


    public List<Order> getUsersOrder(Long userid) throws Exception {
        return orderRepository.findByCustomerId(userid);
    }


    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception {
        List<Order> orders= orderRepository.findByRestaurantId(restaurantId);

        if (orderStatus!=null){
            orders=orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).toList();
        }
        return orders;
    }


    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder=orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()){
            throw new Exception("Ordine non trovato");
        }
        return optionalOrder.get();
    }
}
