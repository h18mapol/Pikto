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
public class AdminCrud implements IAdminCrud{
    private Connection con;

    @Override
    public List<Product> getAllProducts() {
   List <Product> productList=new ArrayList<>();
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlSelectEvents="SELECT * FROM product";
            ResultSet resultset=statement.executeQuery(sqlSelectEvents);
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
     }    

    @Override
    public Product getProduct(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product deleteProduct(int productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product addProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
    }

    @Override
    public User getUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User updateUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addUser(User user) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            String sqlAddUser = "INSERT INTO user (firstName, lastName, mobileNr, email, password, admin, seller, pictureUrl) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddUser);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getMobileNr());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getAdmin());
            statement.setInt(7, user.getSeller());
            statement.setString(8, user.getPictureUrl());
            statement.executeUpdate();

            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
    }

    @Override
    public List<Product_review> getAllReviews() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product_review getReview(int reviewId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product_review updateReview(Product_review product_review) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product_review deleteReview(int reviewId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
     }    
    
    @Override
    public Order getOrder(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order updateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
