package piktoproject.pikto.repositorys;

import java.util.List;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;

public interface IUserCrud {
    public List<Product>getAllProducts();
    public Product getProduct(int productId, int userId);
    public Product updateProduct(Product product);
    public void deleteProduct(int productId);
    public Product addProduct(Product product);

    public List<Product_review>getAllReviews(int userId);
    public Product_review getReview(int reviewId, int userId);
    public Product_review updateReview(Product_review product_review);
    public void deleteReview(int reviewId);
}
