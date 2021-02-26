package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.*;

import java.util.List;


public interface IShoppingFunctions {
    //Cart Functions
    void createCart(String sessionId, User user);
    Cart getCart(String sessionId);
    void deleteCart(Cart cart);

    //Cart Items Methods
    List<CartItem> getAllCartItems(Cart cart);
    List<CartItemDTO> getAllCartItemsDTO(Cart cart);
    void addToCart(CartItem cartItem);
    void deleteCartItem(CartItem cartItem);
    void emptyCart(Cart cart);

    //Order Functions
    void createOrder(Cart cart, User user);
    void createOrderPaypal(Order order);
    //Transaction Functions
    Transaction createTransaction(Order order);
}
