package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.AddCartItemRequest;
import lucavigano.deliveryapp.DTO.UpdateCartItemRequest;
import lucavigano.deliveryapp.entities.Cart;
import lucavigano.deliveryapp.entities.CartItem;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    @ResponseStatus(HttpStatus.OK)
    public CartItem addItemToCart(@RequestBody AddCartItemRequest req,
                                  @AuthenticationPrincipal User currentUser) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, currentUser);
        return cartItem;
    }

    @PutMapping("/cart-item/update")
    @ResponseStatus(HttpStatus.OK)
    public CartItem updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                           @AuthenticationPrincipal User currentUser) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return cartItem;
    }

    @DeleteMapping("/cart-item/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    public Cart removeCartItem(@PathVariable Long id,
                               @AuthenticationPrincipal User currentUser) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, currentUser);
        return cart;
    }

    @PutMapping("/cart/clear")
    @ResponseStatus(HttpStatus.OK)
    public Cart clearCart(@AuthenticationPrincipal User currentUser) throws Exception {
        Cart cart = cartService.clearCart(currentUser.getId());
        return cart;
    }

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public Cart findUserCart(@AuthenticationPrincipal User currentUser) throws Exception {
        Cart cart = cartService.findCartByUserId(currentUser.getId());
        return cart;
    }
}
