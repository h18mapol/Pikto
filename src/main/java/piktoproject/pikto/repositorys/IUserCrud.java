package piktoproject.pikto.repositorys;

import java.util.List;

import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;

public interface IUserCrud{
    //User
    User getUserById(int userId);
    User updateUser(User user);
    void deleteUser(int userId);
    void addUser(User user);

    //Products
    List<Product>getAllProductsById(int userId);
    Product getProduct(int productId);
    Product updateProduct(Product product);
    void deleteProduct(int productId);
    void addProduct(Product product);

    //Reviews
    List<Product_review>getAllReviewsById(int userId);
    Product_review getReview(int reviewId);
    Product_review updateReview(Product_review product_review);
    void deleteReview(int reviewId);

    //Orders
    List<Order>getAllOrdersById(int userId);
    Order getOrderById(int orderId);
    Order updateOrder(Order order);
    void DeleteOrder(int orderId);
}
