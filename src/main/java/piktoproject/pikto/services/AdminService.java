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

    public List<Product>getAllProducts(){

    return adminCrud.getAllProducts();
    }

   public Product getProduct(int productId){
        return adminCrud.getProduct(productId);
   }

   public Product updateProduct(Product product){
        return adminCrud.updateProduct(product);
   }

   public Product deleteProduct(int productId){
        return adminCrud.deleteProduct(productId);
   }

   public Product addProduct(Product product){
        return adminCrud.addProduct(product);
   }


   public List<User>getAllUsers(){
   return adminCrud.getAllUsers();
   }

   public User getUser(int userId){
        return adminCrud.getUser(userId);
   }

   public User updateUser(User user){
        return adminCrud.updateUser(user);
   }

   public void deleteUser(int userId){
         adminCrud.deleteUser(userId);
   }
   public User addUser(User user){
        return adminCrud.addUser(user);
   }

   public List<Product_review>getAllReviews(){
        return adminCrud.getAllReviews();
   }

   public Product_review getReview(int reviewId){
        return adminCrud.getReview(reviewId);

   }
   public Product_review updateReview(Product_review product_review){
      return adminCrud.updateReview(product_review);
   }

   public Product_review deleteReview(int reviewId){
        return adminCrud.deleteReview(reviewId);
   }
  
 
      
      
}
    

