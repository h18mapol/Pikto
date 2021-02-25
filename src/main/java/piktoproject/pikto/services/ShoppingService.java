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
    public void deleteCartItem(CartItem cartItem) {
        ShoppingFunctionsCrud.deleteCartItem(cartItem);
    }
    public Cart getCart(String sessionId){ return ShoppingFunctionsCrud.getCart(sessionId);}
    public void deleteCart(Cart cart) {ShoppingFunctionsCrud.deleteCart(cart);}
    public void emptyCart(Cart cart){ShoppingFunctionsCrud.emptyCart(cart);}
    public void createCart(String sessionId, User user){ShoppingFunctionsCrud.createCart(sessionId, user);}
    //OrderFunctions
    public List<CartItemDTO> getAllCartItemsDTO(Cart cart) {
        return ShoppingFunctionsCrud.getAllCartItemsDTO(cart);
    }

    public void createOrder(Cart cart, User user){ShoppingFunctionsCrud.createOrder(cart, user);}
    //TransactionFunctions
    public Transaction createTransaction(Order order){return ShoppingFunctionsCrud.createTransaction(order);}
}
