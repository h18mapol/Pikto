package piktoproject.pikto.repositorys;

import java.util.List;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;


public interface IAdminCrud {
       public List<Product>getAllProducts();
       public Product getProduct(int productId);
       public Product updateProduct(Product product);
       public Product deleteProduct(int productId);
       public Product addProduct(Product product);
       
       
       public List<User>getAllUsers();
       public User getUser(int userId);
       public User updateUser(User user);
       public User deleteUser(int userId);
       public User addUser(User user);
       
       public List<Product_review>getAllReviews();
       public Product_review getReview(int reviewId);
       public Product_review updateReview(Product_review product_review);
       public Product_review deleteReview(int reviewId);
       
       
       

}
