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
    } //Klar
    @Override
    public User updateUser(User user) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");

            String sqlUpdateUser = "UPDATE user SET firstName=?, lastName=?, mobileNr=?, email=?, admin=?, seller=?, pictureUrl=?  WHERE userId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateUser);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getMobileNr());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAdmin());
            statement.setInt(5, user.getSeller());
            statement.setString(5, user.getPictureUrl());
            statement.execute();
            statement.close();
            con.close();
            return user;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
        return null;
    } //Klar inte Testad
    @Override
    public void deleteUser(int userId) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteUser = "DELETE FROM user WHERE eventid="+userId;
            statement.executeUpdate(sqlDeleteUser);
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteUser
    } //Klar inte Testad
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
        }//End addUser
    } //Klar
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
                product.setContent(resultSet.getString("content"));
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
    } //Klar
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
    } //Klar
    @Override
    public Product updateProduct(Product product) {
        return null;
    }
    @Override
    public void deleteProduct(int productId) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteProduct = "DELETE FROM product WHERE productId="+productId;
            statement.executeUpdate(sqlDeleteProduct);
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteUser
    }
    @Override
    public void addProduct(Product product) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            String sqlAddUser = "INSERT INTO product (title, summary, type, price, discount, content, productUrl) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddUser);
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getSummary());
            statement.setInt(3, product.getType());
            statement.setFloat(4, product.getPrice());
            statement.setFloat(5, product.getDiscount());
            statement.setString(6, product.getContent());
            statement.setString(7, product.getProductUrl());
            statement.executeUpdate();

            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addUser
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
    } //Klar
    @Override
    public Product_review getReview(int reviewId) {
        return null;
    }
    @Override
    public Product_review updateReview(Product_review product_review) {
        return null;
    }
    @Override
    public void deleteReview(int reviewId) {

    }
    //Orders
    @Override
    public List<Order> getAllOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");


            String sqlgetAllOrders = "SELECT * FROM piktodb.order where userId=?";
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
    } //Klar
    @Override
    public Order getOrderById(int orderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Order updateOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void DeleteOrder(int orderId) {

    }
}
