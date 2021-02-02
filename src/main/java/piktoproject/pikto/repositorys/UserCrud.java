package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;
import piktoproject.pikto.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserCrud implements IUserCrud {
    private Connection con;

    @Override
    public User getUserById(int userId) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetUserById = "SELECT * FROM user WHERE userId=?";
            PreparedStatement statement = con.prepareStatement(sqlgetUserById);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("userId"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName")); //eventdate
                user.setMobileNr(resultSet.getString("mobileNr"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getInt("admin"));
                user.setSeller(resultSet.getInt("seller"));
                user.setSeller(resultSet.getInt("content"));
                user.setPictureUrl(resultSet.getString("pictureUrl"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return user;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
        return null;
    }

    @Override
    public List<Product> getAllProducts(int userId) {
        List<Product> products = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllProducts = "SELECT * FROM product WHERE userId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("productId"));
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getInt("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setProductUrl(resultSet.getString("productUrl"));
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
    }

    @Override
    public Product getProduct(int productId) {

        List<Product> products = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllProducts = "SELECT * FROM product WHERE productId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();
            Product product = new Product();

            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getInt("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setProductUrl(resultSet.getString("productUrl"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return product;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(int productId) {

    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product_review> getAllReviews(int userId) {
        List<Product_review> product_reviews = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllReviews = "SELECT * FROM product_review WHERE userId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllReviews);
            statement.setInt(1, userId);

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
    }

    @Override
    public Product_review getReview(int reviewId, int userId) {
        return null;
    }

    @Override
    public Product_review updateReview(Product_review product_review) {
        return null;
    }

    @Override
    public void deleteReview(int reviewId) {

    }

    @Override
    public List<Order> getAllOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllOrders = "SELECT * FROM order WHERE userId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllOrders);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Order order=new Order();
                order.setOrderId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setSessionId(resultSet.getString("sessionId"));
                order.setStatus(resultSet.getInt("status"));
                order.setSubTotal(resultSet.getInt("subTotal"));
                order.setItemDiscount(resultSet.getFloat("itemDiscount"));
                order.setTax(resultSet.getFloat("tax"));
                order.setShipping(resultSet.getFloat("shipping"));
                order.setTotal(resultSet.getFloat("total"));
                order.setPromo(resultSet.getFloat("promo"));
                order.setDiscount(resultSet.getInt("discount"));
                order.setGrandTotal(resultSet.getInt("grandTotal"));
                order.setFirstName(resultSet.getString("firstName"));
                order.setLastName(resultSet.getString("lastName"));
                order.setMobile(resultSet.getInt("mobile"));
                order.setEmail(resultSet.getString("email"));
                order.setAddress(resultSet.getString("address"));
                order.setCity(resultSet.getString("city"));
                order.setCreatedAt(resultSet.getString("createdAt"));
                order.setContent(resultSet.getString("content"));
                orders.add(order);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return orders;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    }
}
