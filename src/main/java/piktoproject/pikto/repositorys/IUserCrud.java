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
    Product_review getReviewById(int reviewId);
    void addReview(Product_review product_review);
    Product_review updateReview(Product_review product_review);
    void deleteReview(int reviewId);

    //Orders
    List<Order>getAllUserOrders(int userId);
    void addOrder(Order order);
    Order getOrderById(int orderId);
    Order updateOrder(Order order);
    void deleteOrder(int orderId);
     List<Product>getAllOrderItems(int userId);


    //Category
    List<Category>getAllCategories();
    void addCategory(Category category);
    Category getCategoryById(int categoryId);
    Category updateCategory(Category category);
    void DeleteCategory(int categoryId);

}
