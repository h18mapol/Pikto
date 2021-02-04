package piktoproject.pikto.repositorys;

import java.util.List;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;


public interface IAdminCrud extends IUserCrud{
    public List<User>getAllUsers();
    public List<Product>getAllProducts();
    public List<Product_review>getAllReviews();
    public List<Order>getAllOrders();
}
