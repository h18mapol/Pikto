package piktoproject.pikto.repositorys;

import java.util.List;

import piktoproject.pikto.models.*;

public interface IUserCrud{
    //User
    User getUserById(int userId);
    User updateUser(User user);
    void deleteUser(int userId);
    void addUser(User user);
    User getUserByEmail(String email);

    //Products
    List<Product>getAllCategoryProducts(int categoryId);
    List<Product>getAllUserProducts(int userId);
    Product getProduct(int productId);
    Product updateProduct(Product product);
    void deleteProduct(int productId);
    void addProduct(Product product);

    //Reviews
    List<Product_review>getAllUserReviews(int userId);
     List<Product_review>getAllProductReviews(int productId);
    Product_review getReview(int reviewId);
    void addReview(Product_review product_review);
    Product_review updateReview(Product_review product_review);
    void deleteReview(int reviewId);

    //Orders
    List<Order>getAllUserOrders(int userId);
    void addOrder(Order order);
    Order getOrderById(int orderId);
    Order updateOrder(Order order);
    void DeleteOrder(int orderId);

    //Category
    List<Category>getAllCategories();
    void addCategory(Category category);
    Order getCategoryById(int categoryId);
    Order updateCategory(int categoryId);
    void DeleteCategory(int categoryId);

}
