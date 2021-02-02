/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piktoproject.pikto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.repositorys.IUserCrud;

/**
 *
 * @author Henrik
 */
@Service
public class UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private IUserCrud userCrud;

    public List<Product>getAllProducts(int userId){

        return userCrud.getAllProducts(userId);
    }

    public Product getProduct(int productId){
        return userCrud.getProduct(productId);
    }

    public Product updateProduct(Product product){
        return userCrud.updateProduct(product);
    }

    public void deleteProduct(int productId){
        userCrud.deleteProduct(productId);
    }

    public Product addProduct(Product product){
        return userCrud.addProduct(product);
    }

    public List<Product_review>getAllReviews(int userId){
        return userCrud.getAllReviews(userId);
    }

    public Product_review getReview(int reviewId, int userId){
        return userCrud.getReview(reviewId, userId);
    }

    public Product_review updateReview(Product_review product_review){
        return updateReview(product_review);
    }

    public void deleteReview(int reviewId){
        userCrud.deleteReview(reviewId);
    }
}



