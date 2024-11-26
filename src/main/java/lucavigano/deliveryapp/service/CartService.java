package lucavigano.deliveryapp.service;

import lucavigano.deliveryapp.DTO.AddCartItemRequest;
import lucavigano.deliveryapp.entities.Cart;
import lucavigano.deliveryapp.entities.CartItem;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String token) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws Exception;

    public Cart removeItemFromCart(Long cartItem, String token) throws Exception;

    public Long calculateCartTotal(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public Cart clearCart(Long userId) throws Exception;
}
