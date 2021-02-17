package piktoproject.pikto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.CartItem;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Cart;
import piktoproject.pikto.repositorys.IAdminCrud;
import piktoproject.pikto.repositorys.IShoppingFunctions;

import java.util.List;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

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
        
    };
    
    //OrderFunctions

}
