package piktoproject.pikto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.CartItem;
import piktoproject.pikto.models.Product;
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

    //OrderFunctions

}
