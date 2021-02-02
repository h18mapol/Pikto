package piktoproject.pikto.repositorys;

import piktoproject.pikto.models.Cart;
import piktoproject.pikto.models.CartItem;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Transaction;

import java.util.List;

public interface IShoppingFunctions {
    //Cart Items Methods
    List<CartItem> getAllCartItems(Cart cart);
    void addToCart(CartItem cartItem);
    void deleteFromCart(CartItem cartItem);

    //Cart Functions
    Cart getCart(int sessionId);
    void deleteCart(Cart cart);

    //Order Functions
    Order createOrder(Cart cart);

    //Transaction Functions
    Transaction createTransaction(Order order);
}
