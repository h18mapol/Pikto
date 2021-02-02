package piktoproject.pikto.repositorys;

import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Cart;

public interface IProductFunctions {
    public void addToCart(int productId);
    public void removeFromCart(int productId);
    
    
    public Cart getCart(int sessionId);
    public Order purchase(Cart cart);
    
    
}
