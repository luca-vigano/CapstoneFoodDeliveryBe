package lucavigano.deliveryapp.controllers;

import lucavigano.deliveryapp.DTO.AddCartItemRequest;
import lucavigano.deliveryapp.DTO.UpdateCartItemRequest;
import lucavigano.deliveryapp.entities.Cart;
import lucavigano.deliveryapp.entities.CartItem;
import lucavigano.deliveryapp.entities.User;
import lucavigano.deliveryapp.repository.UserServ;
import lucavigano.deliveryapp.service.CartService;
import lucavigano.deliveryapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private UserServ userService;

    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    @ResponseStatus(HttpStatus.OK)
    public CartItem addItemToCart(@RequestBody AddCartItemRequest req,
                                  @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();

        CartItem cartItem=cartService.addItemToCart(req,jwt);

        return cartItem;
    }

    @PutMapping("/cart-item/update")
    @ResponseStatus(HttpStatus.OK)
    public CartItem updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                            @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        CartItem cartItem=cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return cartItem;
    }

    @DeleteMapping("/cart-item/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    public Cart removeCartItem(@PathVariable Long id,
                               @RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        Cart cart=cartService.removeItemFromCart(id,jwt);
        return cart;
    }

    @PutMapping("/cart/clear")
    @ResponseStatus(HttpStatus.OK)
    public Cart clearCart(@RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.clearCart(user.getId());
        return cart;
    }

    @GetMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public Cart findUserCart(@RequestHeader("Authorization") String token) throws Exception {
        String jwt = token.replace("Bearer ", "").trim();
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.findCartByUserId(user.getId());

        return cart;
    }

}
