package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.*;

import java.util.List;


public interface IShoppingFunctions {

    //Cart Items Methods
    List<CartItem> getAllCartItems(Cart cart);
    void addToCart(CartItem cartItem);
    void deleteFromCart(CartItem cartItem);

    //Cart Functions
    Cart getCart(String sessionId);
    void deleteCart(Cart cart);

    //Order Functions
    void createOrder(Cart cart, User user);

    //Transaction Functions
    Transaction createTransaction(Order order);
}
