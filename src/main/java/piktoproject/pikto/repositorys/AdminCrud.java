/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piktoproject.pikto.repositorys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;

/**
 *
 * @author Henrik
 */
@Repository
public class AdminCrud extends UserCrud implements IAdminCrud {
    private Connection con;
    @Override
    public List<Product> getAllProducts() {
   List <Product> productList=new ArrayList<>();
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlgetAllProducts = "SELECT * \n" +
                    "FROM product\n" +
                    "INNER JOIN product_category ON product.productId=product_category.productId";
            ResultSet resultset=statement.executeQuery(sqlgetAllProducts);
            while (resultset.next()){
                Product product=new Product();
                product.setProductId(resultset.getInt(1));
                product.setUserId(resultset.getInt(2));
                product.setTitle(resultset.getString(3));
                product.setSummary(resultset.getString(4));
                product.setType(resultset.getInt(5));
                product.setPrice(resultset.getFloat(6));
                product.setDiscount(resultset.getFloat(7));
                product.setPublishedAt(resultset.getString(8));
                product.setContent(resultset.getString(9));
                product.setProductUrl(resultset.getString(10));
                product.setCategoryId(resultset.getInt("categoryId"));
                productList.add(product);
            }

            resultset.close();
            statement.close();
            con.close();
            return productList;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     } //Klar
    @Override
    public List<User> getAllUsers() {
      List <User> userList=new ArrayList<>();
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlSelectOrders="SELECT * FROM `user`";
            ResultSet resultset=statement.executeQuery(sqlSelectOrders);
            while (resultset.next()){
                User user=new User();
                user.setUserId(resultset.getInt(1));
                user.setFirstName(resultset.getString(2));
                user.setLastName(resultset.getString(3));
                user.setMobileNr(resultset.getString(4));
                user.setEmail(resultset.getString(5));
                user.setAdmin(resultset.getInt(7));
                user.setSeller(resultset.getInt(8));
                user.setPictureUrl(resultset.getString(9));

                   userList.add(user);
            }

            resultset.close();
            statement.close();
            con.close();
            return userList;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } //Klar
    @Override
    public List<Product_review> getAllReviews() {
        List<Product_review> product_reviews = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllReviews = "SELECT * FROM product_review";
            PreparedStatement statement = con.prepareStatement(sqlgetAllReviews);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product_review product_review = new Product_review();
                product_review.setReviewId(resultSet.getInt("reviewId"));
                product_review.setProductId(resultSet.getInt("productId"));
                product_review.setTitle(resultSet.getString("title"));
                product_review.setRating(resultSet.getInt("rating"));
                product_review.setCreatedAt(resultSet.getString("createdAt"));
                product_review.setContent(resultSet.getString("content"));
                product_review.setUserId(resultSet.getInt("userId"));
                product_reviews.add(product_review);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return product_reviews;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar
    @Override
    public List<Order> getAllOrders() {
     List <Order> orderList=new ArrayList<>();
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlSelectOrders="SELECT * FROM `order`";
            ResultSet resultset=statement.executeQuery(sqlSelectOrders);
            while (resultset.next()){
                Order order=new Order();
                order.setOrderId(resultset.getInt(1));
                order.setUserId(resultset.getInt(2));
                order.setSessionId(resultset.getString(3));
                order.setStatus(resultset.getInt(4));
                order.setSubTotal(resultset.getInt(5));
                order.setItemDiscount(resultset.getFloat(6));
                order.setTax(resultset.getFloat(7));
                order.setShipping(resultset.getFloat(8));
                order.setTotal(resultset.getFloat(9));
                order.setPromo(resultset.getFloat(10));
                order.setDiscount(resultset.getInt(11));
                order.setGrandTotal(resultset.getInt(12));
                order.setFirstName(resultset.getString(13));
                order.setLastName(resultset.getString(14));
                order.setMobile(resultset.getInt(15));
                order.setEmail(resultset.getString(16));
                order.setAddress(resultset.getString(17));
                order.setCity(resultset.getString(18));
                order.setCreatedAt(resultset.getString(19));
                order.setContent(resultset.getString(20));
                      

                   orderList.add(order);
            }
         
            resultset.close();
            statement.close();
            con.close();
            return orderList;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     } //Klar
<<<<<<< HEAD

    @Override
    public User getLoggedInUser() {
        System.out.println("USER --> /USER");
        OAuth2AuthorizedClientService clientService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken){
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            if (clientRegistrationId.equalsIgnoreCase( "github")){
                String githubEmail = (String) oauthToken.getPrincipal().getAttributes().get("login")+"@github.com";
                return getUserByEmail(githubEmail);
            }
            String email = (String)oauthToken.getPrincipal().getAttributes().get("email");
            System.out.println(email);
            return getUserByEmail(email);
        } else {
            System.out.println("Normal Login");
            return getUserByEmail(authentication.getName());
        }
    }

=======
    @Override
    public List<Product> getAllProductsBySearch(String SearchWord) {
 List<Product> products = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

     String sqlgetAllProducts = "SELECT * FROM product INNER JOIN product_category ON product.productId=product_category.productId WHERE title LIKE ?";

            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
           statement.setString(1, "'%Bok%'");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getInt("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setContent(resultSet.getString("content"));
                product.setProductUrl(resultSet.getString("productUrl"));
                product.setCategoryId(resultSet.getInt("categoryId"));
                products.add(product);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return products;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar
>>>>>>> 44b32bba4675426c16be5a10385f6af50e85a712
}
