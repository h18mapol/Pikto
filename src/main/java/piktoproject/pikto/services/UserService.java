/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piktoproject.pikto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;


import piktoproject.pikto.models.User;
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

    //User Methods
    public User getUserById(int userId){
        return userCrud.getUserById(userId);
    }
    
     public void addUser(User user){
        userCrud.addUser(user);
    }


    //Product Methods
    public List<Product>getAllUserProducts(int userId){
        return userCrud.getAllUserProducts(userId);
    }
    public List<Product>getAllCategoryProducts(int categoryId){
        return userCrud.getAllCategoryProducts(categoryId);
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
    public void addProduct(Product product){
        userCrud.addProduct(product);
    }
    //Review Methods
    public List<Product_review>getAllUserReviews(int userId){
        return userCrud.getAllUserReviews(userId);
    }
      public List<Product_review>getAllProductReviews(int productId){
        return userCrud.getAllProductReviews(productId);
    }
    public Product_review getReview(int reviewId){
        return userCrud.getReview(reviewId);
    }
    public Product_review updateReview(Product_review product_review){
        return updateReview(product_review);
    }
    public void deleteReview(int reviewId){
        userCrud.deleteReview(reviewId);
    }

    //Order Methods
    public List<Order> getAllUserOrders(int userId){
        return userCrud.getAllUserOrders(userId);
    }
}



