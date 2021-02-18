package piktoproject.pikto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.*;
import piktoproject.pikto.repositorys.IAdminCrud;
import piktoproject.pikto.repositorys.IShoppingFunctions;

import java.util.List;

@Service
public class ShoppingService {

    @Autowired
    private IShoppingFunctions ShoppingFunctionsCrud;

    //Cart Methods
    public void addToCart(CartItem cartItem) {
        ShoppingFunctionsCrud.addToCart(cartItem);
    }
    public List<CartItem> getAllCartItems(Cart cart) {
        return ShoppingFunctionsCrud.getAllCartItems(cart);
    }
    public void deleteFromCart(CartItem cartItem) {
        ShoppingFunctionsCrud.deleteFromCart(cartItem);
    }
    public Cart getCart(String sessionId){ return ShoppingFunctionsCrud.getCart(sessionId);}
    public void deleteCart(Cart cart) {ShoppingFunctionsCrud.deleteCart(cart);}
    //OrderFunctions
    public Order createOrder(Cart cart, User user){return ShoppingFunctionsCrud.createOrder(cart,user);}
    //TransactionFunctions
    public Transaction createTransaction(Order order){return ShoppingFunctionsCrud.createTransaction(order);}
}
