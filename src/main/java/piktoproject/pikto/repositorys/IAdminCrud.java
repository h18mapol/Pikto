package piktoproject.pikto.repositorys;

import java.util.List;

import piktoproject.pikto.models.*;


public interface IAdminCrud extends IUserCrud{
    List<User>getAllUsers();
    List<Product>getAllProducts();
    List<Product>getAllProductsBySearch(String SearchWord);
    
    List<Product>getAllProductsByCategory(int categoryId);

    List<Product_review>getAllReviews();
    List<Order>getAllOrders();
    User getLoggedInUser();
    
    void sendEmail(User user);
    boolean changePassword(PasswordDTO passwordDTO);
}
