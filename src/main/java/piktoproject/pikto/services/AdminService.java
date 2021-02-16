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
import piktoproject.pikto.repositorys.IAdminCrud;

/**
 *
 * @author Henrik
 */
@Service
public class AdminService {
    @Autowired
    private IAdminCrud adminCrud;

    //Users
    public List<User>getAllUsers(){
        return adminCrud.getAllUsers();
    }
    public User getLoggedInUser(){
        return adminCrud.getLoggedInUser();
    }
    public User getUser(int userId){
        return adminCrud.getUserById(userId);
    }
    public User updateUser(User user){
        return adminCrud.updateUser(user);
    }
    public void deleteUser(int userId){
        adminCrud.deleteUser(userId);
    }
    public void addUser(User user){
        adminCrud.addUser(user);
    }
    public User getUserByEmail(String email){return adminCrud.getUserByEmail(email);}

    //Products
    public List<Product>getAllProducts(){

    return adminCrud.getAllProducts();
    }
    public List<Product>getAllProductsbyId(int userId){
        return adminCrud.getAllUserProducts(userId);
    }
    public Product getProduct(int productId){
        return adminCrud.getProduct(productId);
    }
    public Product updateProduct(Product product){
        return adminCrud.updateProduct(product);
    }
    public void deleteProduct(int productId){
       adminCrud.deleteProduct(productId);
    }
    public Product addProduct(Product product){
        adminCrud.addProduct(product);
        return product;
    }

    //Reviews
    public List<Product_review>getAllReviews(){
        return adminCrud.getAllReviews();
    }
    public List<Product_review>getAllReviewsById(int userId){
        return adminCrud.getAllUserReviews(userId);
    }
    public Product_review getReview(int reviewId){
        return adminCrud.getReview(reviewId);
    }
    public Product_review updateReview(Product_review product_review){
      return adminCrud.updateReview(product_review);
    }
    public void deleteReview(int reviewId){
        adminCrud.deleteReview(reviewId);
    }
    public List<Order>getAllOrders(){
        return adminCrud.getAllOrders();
    }

    //Orders
    public List<Order>getAllOrdersById(int userId){return adminCrud.getAllUserOrders(userId);}
    public Order getOrder(int orderId){
        return adminCrud.getOrderById(orderId);
    }
    public Order updateOrder(Order order){
        return adminCrud.updateOrder(order);
    }
  
  
   
   
      
      
}
    

