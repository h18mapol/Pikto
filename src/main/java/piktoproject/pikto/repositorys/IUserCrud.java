package piktoproject.pikto.repositorys;

import java.util.List;

import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;

public interface IUserCrud {
    //User
    User getUserById(int userId);
    //Products
    List<Product>getAllProducts(int userId);
    Product getProduct(int productId);
    Product updateProduct(Product product);
    void deleteProduct(int productId);
    Product addProduct(Product product);
    //Reviews
    List<Product_review>getAllReviews(int userId);
    Product_review getReview(int reviewId, int userId);
    Product_review updateReview(Product_review product_review);
    void deleteReview(int reviewId);
    //Orders
    List<Order>getAllOrders(int userId);
}
