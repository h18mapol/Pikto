package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.Cart;
import piktoproject.pikto.models.CartItem;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Transaction;

import java.util.List;

@Repository
public class ShoppingFunctions implements IShoppingFunctions {
    @Override
    public List<CartItem> getAllCartItems(Cart cart) {
        return null;
    }

    @Override
    public void addToCart(CartItem cartItem) {

    }

    @Override
    public void deleteFromCart(CartItem cartItem) {

    }

    @Override
    public Cart getCart(int sessionId) {
        return null;
    }

    @Override
    public void deleteCart(Cart cart) {

    }

    @Override
    public Order createOrder(Cart cart) {
        return null;
    }

    @Override
    public Transaction createTransaction(Order order) {
        return null;
    }
}
