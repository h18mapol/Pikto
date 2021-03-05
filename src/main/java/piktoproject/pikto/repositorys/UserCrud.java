package piktoproject.pikto.repositorys;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserCrud implements IUserCrud {
    private Connection con;

    //User
    @Override
    public User getUserById(int userId) {
        try {
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
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End End GetUserById
        return null;
    } //Klar

    @Override
    public User updateUser(User user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlUpdateUser = "UPDATE user SET firstName=?, lastName=?, mobileNr=?, email=?, admin=?, seller=?, pictureUrl=?  WHERE userId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateUser);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getMobileNr());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAdmin());
            statement.setInt(6, user.getSeller());
            statement.setString(7, user.getPictureUrl());
            statement.setInt(8, user.getUserId());
            statement.execute();
            statement.close();
            con.close();
            return user;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
        return null;
    } //Klar inte Testad

    @Override
    public void deleteUser(int userId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteUser = "DELETE FROM piktodb.user WHERE userId=" + userId;
            statement.executeUpdate(sqlDeleteUser);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteUser
    } //Klar

    @Override
    public void addUser(User user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddUser = "INSERT INTO user (firstName, lastName, mobileNr, email, password, admin, pictureUrl) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddUser);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getMobileNr());
            statement.setString(4, user.getEmail());
            statement.setString(5, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            statement.setInt(6, user.getAdmin());
            statement.setString(7, user.getPictureUrl());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addUser
    } //Klar

    @Override
    public User getUserByEmail(String email) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetUserById = "SELECT * FROM user WHERE email=?";
            PreparedStatement statement = con.prepareStatement(sqlgetUserById);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("userId"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName")); //eventdate
                user.setMobileNr(resultSet.getString("mobileNr"));
                user.setEmail(resultSet.getString("email"));
                user.setAdmin(resultSet.getInt("admin"));
                user.setPassword(resultSet.getString("password"));
                user.setSeller(resultSet.getInt("seller"));
                user.setPictureUrl(resultSet.getString("pictureUrl"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return user;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
        return null;
    } //Klar

    //Product
    @Override
    public List<Product> getAllUserProducts(int userId) {
        List<Product> products = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllProducts = "SELECT * \n" +
                    "FROM product\n" +
                    "INNER JOIN product_category ON product.productId=product_category.productId\n" +
                    "WHERE userId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getString("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setContent("");
                product.setProductUrl(resultSet.getString("productUrl"));
                product.setCategoryId(resultSet.getInt("categoryId"));
                products.add(product);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return products;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar

    @Override
    public List<Product> getAllCategoryProducts(int categoryId) {
        List<Product> products = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllProducts = "SELECT product.title\n" +
                    "FROM product\n" +
                    "INNER JOIN product_category\n" +
                    "ON product.productId = product_category.productId AND product_category.categoryId  = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
            statement.setInt(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getString("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setContent(resultSet.getString("content"));
                product.setCategoryId(resultSet.getInt("categoryId"));
                product.setProductUrl(resultSet.getString("productUrl"));
                products.add(product);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return products;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar

    @Override
    public Product getProduct(int productId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllProducts = "SELECT * \n" +
                    "FROM product\n" +
                    "INNER JOIN product_category ON product.productId=product_category.productId\n" +
                    "WHERE product.productId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllProducts);
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();
            Product product = new Product();

            while (resultSet.next()) {
                product.setProductId(resultSet.getInt("productId"));
                product.setUserId(resultSet.getInt("userId"));
                product.setTitle(resultSet.getString("title"));
                product.setSummary(resultSet.getString("summary"));
                product.setType(resultSet.getString("type"));
                product.setPrice(resultSet.getInt("price"));
                product.setDiscount(resultSet.getInt("discount"));
                product.setContent("");
                product.setCategoryId(resultSet.getInt("categoryId"));
                product.setPublishedAt(resultSet.getString("publishedAt"));
                product.setProductUrl(resultSet.getString("productUrl"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return product;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getProductById
        return null;
    } //Klar

    @Override
    public Product updateProduct(Product product) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlUpdateEvent = "UPDATE product SET userId=?, title=?, summary=?, type=?, price=?, discount=?, publishedAt=?, content=?, productUrl=? WHERE productId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateEvent);
            statement.setInt(1, product.getUserId());
            statement.setString(2, product.getTitle());
            statement.setString(3, product.getSummary());
            statement.setString(4, product.getType());
            statement.setFloat(5, product.getPrice());
            statement.setFloat(6, product.getDiscount());
            statement.setString(7, product.getPublishedAt());
            statement.setString(8, product.getContent());
            statement.setString(9, product.getProductUrl());
            statement.setInt(10, product.getProductId());
            statement.execute();
            statement.close();
            con.close();
            return product;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End events
        return null;
    } //Klar

    @Override
    public void deleteProduct(int productId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteProduct = "DELETE FROM product WHERE productId=" + productId;
            statement.executeUpdate(sqlDeleteProduct);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteProduct
    } //Klar

    @Override
    public void addProduct(Product product) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddUser = "SELECT add_product(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddUser);
            statement.setInt(1, product.getUserId());
            statement.setString(2, product.getTitle());
            statement.setString(3, product.getSummary());
            statement.setString(4, product.getType());
            statement.setFloat(5, product.getPrice());
            statement.setFloat(6, product.getDiscount());
            statement.setString(7, product.getContent());
            statement.setString(8, product.getProductUrl());
            statement.setInt(9, product.getCategoryId());
            statement.executeQuery();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addUser
    } //Klar

    //Reviews
    @Override
    public List<Product_review> getAllUserReviews(int userId) {
        List<Product_review> product_reviews = new ArrayList<>();
        System.out.println("REVIEW !!!!!! ----->>>>>> " + userId);
        try {
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
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar

    @Override
    public Product_review getReviewById(int reviewId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetReviewById = "SELECT * FROM product_review WHERE reviewId=?";
            PreparedStatement statement = con.prepareStatement(sqlgetReviewById);
            statement.setInt(1, reviewId);

            ResultSet resultSet = statement.executeQuery();
            Product_review product_review = new Product_review();

            while (resultSet.next()) {
                product_review.setReviewId(resultSet.getInt("reviewId"));
                product_review.setProductId(resultSet.getInt("productId"));
                product_review.setTitle(resultSet.getString("title"));
                product_review.setRating(resultSet.getInt("rating"));
                product_review.setCreatedAt(resultSet.getString("createdAt"));
                product_review.setContent(resultSet.getString("content"));
                product_review.setUserId(resultSet.getInt("userId"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return product_review;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getReview
        return null;
    } //Klar

    @Override
    public void addReview(Product_review product_review) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddReview = "INSERT INTO product_review (productId, title, rating, content, userId) VALUES(?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddReview);
            statement.setInt(1, product_review.getProductId());
            statement.setString(2, product_review.getTitle());
            statement.setInt(3, product_review.getRating());
            statement.setString(4, product_review.getContent());
            statement.setInt(5, product_review.getUserId());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addUser
    } //Klar

    @Override
    public Product_review updateReview(Product_review product_review) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlUpdateReview = "UPDATE product_review SET productId=?, title=?, rating=?, createdAt=?, content=?, userId=? WHERE reviewId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateReview);
            statement.setInt(1, product_review.getProductId());
            statement.setString(2, product_review.getTitle());
            statement.setInt(3, product_review.getRating());
            statement.setString(4, product_review.getCreatedAt());
            statement.setString(5, product_review.getContent());
            statement.setInt(6, product_review.getUserId());
            statement.setInt(7, product_review.getReviewId());
            statement.execute();
            statement.close();
            con.close();
            return product_review;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End updateReview
        return null;
    } //Klar

    @Override
    public void deleteReview(int reviewId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteReview = "DELETE FROM product_review WHERE reviewId=" + reviewId;
            statement.executeUpdate(sqlDeleteReview);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteReview
    } //Klar

    //Orders
    @Override
    public List<Order> getAllUserOrders(int userId) {
        List<Order> orders = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlgetAllOrders = "SELECT * FROM piktodb.order where userId=?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllOrders);
            statement.setInt(1, userId);
            ResultSet resultset = statement.executeQuery();
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
                order.setPromo(resultset.getString(10));
                order.setDiscount(resultset.getInt(11));
                order.setGrandTotal(resultset.getInt(12));
                order.setFirstName(resultset.getString(13));
                order.setLastName(resultset.getString(14));
                order.setMobile(resultset.getString(15));
                order.setEmail(resultset.getString(16));
                order.setAddress(resultset.getString(17));
                order.setCity(resultset.getString(18));
                order.setCreatedAt(resultset.getString(19));
                order.setContent(resultset.getString(20));
                orders.add(order);
            } //End while
            resultset.close();
            statement.close();
            con.close();
            return orders;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar

    @Override
    public void addOrder(Order order) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddOrder = "INSERT INTO `order` (userId, sessionId, status, subTotal, itemDiscount, tax, shipping, total, promo, discount, grandTotal, firstName, lastName, mobile, email, address, city, createdAt, content) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddOrder);
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getSessionId());
            statement.setInt(3, order.getStatus());
            statement.setDouble(4, order.getSubTotal());
            statement.setDouble(5, order.getItemDiscount());
            statement.setDouble(6, order.getTax());
            statement.setDouble(7, order.getShipping());
            statement.setDouble(8, order.getTotal());
            statement.setString(9, order.getPromo());
            statement.setDouble(10, order.getDiscount());
            statement.setDouble(11, order.getGrandTotal());
            statement.setString(12, order.getFirstName());
            statement.setString(13, order.getLastName());
            statement.setString(14, order.getMobile());
            statement.setString(15, order.getEmail());
            statement.setString(16, order.getAddress());
            statement.setString(17, order.getCity());
            statement.setString(18, order.getCreatedAt());
            statement.setString(19, order.getContent());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addOrder
    } //Klar

    @Override
    public Order getOrderById(int orderId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlGetOrderById = "SELECT * FROM `order` WHERE orderId=?";
            PreparedStatement statement = con.prepareStatement(sqlGetOrderById);
            statement.setInt(1, orderId);

            ResultSet resultSet = statement.executeQuery();
            Order order = new Order();
            while (resultSet.next()) {
                order.setUserId(resultSet.getInt("orderId"));
                order.setUserId(resultSet.getInt("userId"));
                order.setUserId(resultSet.getInt("sessionId"));
                order.setUserId(resultSet.getInt("status"));
                order.setUserId(resultSet.getInt("subTotal"));
                order.setUserId(resultSet.getInt("itemDiscount"));
                order.setUserId(resultSet.getInt("tax"));
                order.setUserId(resultSet.getInt("shipping"));
                order.setUserId(resultSet.getInt("total"));
                order.setUserId(resultSet.getInt("promo"));
                order.setUserId(resultSet.getInt("discount"));
                order.setUserId(resultSet.getInt("grandTotal"));
                order.setUserId(resultSet.getInt("firstName"));
                order.setUserId(resultSet.getInt("lastName"));
                order.setUserId(resultSet.getInt("mobile"));
                order.setUserId(resultSet.getInt("email"));
                order.setUserId(resultSet.getInt("address"));
                order.setUserId(resultSet.getInt("city"));
                order.setUserId(resultSet.getInt("createdAt"));
                order.setUserId(resultSet.getInt("content"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return order;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End End GetUserById
        return null;
    } //Klar

    @Override
    public Order updateOrder(Order order) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlUpdateOrder = "UPDATE `order` SET userId=?, sessionId=?, status=?, subTotal=?, itemDiscount=?, tax=?, shipping=?, total=?, promo=?, discount=?, grandTotal=?, firstName=?, lastName=?, mobile=?, email=?, address=?, city=?, createdAt=?, content=? WHERE orderId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateOrder);
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getSessionId());
            statement.setInt(3, order.getStatus());
            statement.setDouble(4, order.getSubTotal());
            statement.setDouble(5, order.getItemDiscount());
            statement.setDouble(6, order.getTax());
            statement.setDouble(7, order.getShipping());
            statement.setDouble(8, order.getTotal());
            statement.setString(9, order.getPromo());
            statement.setDouble(10, order.getDiscount());
            statement.setDouble(11, order.getGrandTotal());
            statement.setString(12, order.getFirstName());
            statement.setString(13, order.getLastName());
            statement.setString(14, order.getMobile());
            statement.setString(15, order.getEmail());
            statement.setString(16, order.getAddress());
            statement.setString(17, order.getCity());
            statement.setString(18, order.getCreatedAt());
            statement.setString(19, order.getContent());
            statement.setInt(20, order.getOrderId());
            statement.execute();
            statement.close();
            con.close();
            return order;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End UpdateOrder
        return null;
    } //Klar

    @Override
    public void deleteOrder(int orderId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteOrder = "DELETE FROM `order` WHERE orderId=" + orderId;
            statement.executeUpdate(sqlDeleteOrder);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteOrder
    } //Klar

    //Category
    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlSelectAllCategories = "SELECT * FROM `category`";
            ResultSet resultset = statement.executeQuery(sqlSelectAllCategories);
            while (resultset.next()) {
                Category category = new Category();
                category.setCategoryId(resultset.getInt("categoryId"));
                category.setTitle(resultset.getString("title"));
                category.setContent(resultset.getString("content"));
                categories.add(category);
            }
            resultset.close();
            statement.close();
            con.close();
            return categories;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } //Klar

    @Override
    public void addCategory(Category category) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddCategory = "INSERT INTO `category` (title, content) VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCategory);
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getContent());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addCategory
    } //Klar

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetCategoryById = "SELECT * FROM category WHERE categoryId=?";
            PreparedStatement statement = con.prepareStatement(sqlgetCategoryById);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            Category category = new Category();

            while (resultSet.next()) {
                category.setCategoryId(resultSet.getInt("categoryId"));
                category.setTitle(resultSet.getString("title"));
                category.setContent(resultSet.getString("content"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return category;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getCategoryById
        return null;
    } //Klar

    @Override
    public Category updateCategory(Category category) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlUpdateOCategory = "UPDATE `category` SET title=?, content=? WHERE categoryId=?";
            PreparedStatement statement = con.prepareStatement(sqlUpdateOCategory);
            statement.setString(1, category.getTitle());
            statement.setString(2, category.getContent());
            statement.setInt(3, category.getCategoryId());
            statement.execute();
            statement.close();
            con.close();
            return category;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End UpdateCategory
        return null;
    } //Klar

    @Override
    public void DeleteCategory(int categoryId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteCategory = "DELETE FROM category WHERE categoryId=" + categoryId;
            statement.executeUpdate(sqlDeleteCategory);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteCategory
    } //Klar

    //Helper Functions
    @Override
    public List<Product_review> getAllProductReviews(int productId) {
        List<Product_review> product_reviews = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");

            String sqlgetAllReviews = "SELECT * FROM product_review WHERE productId = ?";
            PreparedStatement statement = con.prepareStatement(sqlgetAllReviews);
            statement.setInt(1, productId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product_review product_review = new Product_review();
                product_review.setReviewId(resultSet.getInt("reviewId"));
                product_review.setProductId(resultSet.getInt("productId"));
                product_review.setTitle(resultSet.getString("title"));
                product_review.setRating(resultSet.getInt("rating"));
                product_review.setCreatedAt(resultSet.getString("createdAt"));
                product_review.setContent(resultSet.getString("content"));
                int userID = resultSet.getInt("userId");
                product_review.setUserId(userID);
                product_review.setPictureUrl(getUserById(userID).getPictureUrl());
                product_review.setFirstname(getUserById(userID).getFirstName());
                product_review.setLastname(getUserById(userID).getLastName());

                product_reviews.add(product_review);
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return product_reviews;
        } //end try
        catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End getTeamById
        return null;
    } //Klar

    @Override
    public List<Product> getAllOrderItems(int userId) {
        List<Product> orderItems = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlGetOrderItems = "SELECT * FROM product WHERE product.productId IN (SELECT order_item.productId FROM order_item WHERE order_item.orderId IN ( SELECT piktodb.order.orderId FROM piktodb.order WHERE piktodb.order.userId = ?))";
            PreparedStatement statement = con.prepareStatement(sqlGetOrderItems);
            statement.setInt(1, userId);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                Product product = new Product();
                product.setProductId(resultset.getInt("productId"));
                product.setUserId(resultset.getInt("userId"));
                product.setTitle(resultset.getString("title"));
                product.setSummary(resultset.getString("summary"));
                product.setType(resultset.getString("type"));
                product.setPrice(resultset.getInt("price"));
                product.setDiscount(resultset.getInt("discount"));
                product.setPublishedAt(resultset.getString("publishedAt"));
                product.setContent("");
                product.setProductUrl(resultset.getString("productUrl"));
                orderItems.add(product);
                System.out.println("Order Item Test--------------->>> " + product.getTitle());
            }
            resultset.close();
            statement.close();
            con.close();
            return orderItems;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } //Klar

    @Override
    public Integer getOrderIdBySessionId(String sessionId) {
        System.out.println("Function getOrderIdBySessionId ---------------> Start");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlGetOrderItems = "SELECT orderId FROM `order` WHERE `order`.sessionId = ?";
            PreparedStatement statement = con.prepareStatement(sqlGetOrderItems);
            int orderId = 0;
            statement.setString(1, sessionId);
            ResultSet resultset = statement.executeQuery();
            while (resultset.next()) {
                orderId = resultset.getInt(1);
        }
            resultset.close();
            statement.close();
            con.close();
            System.out.println("OrderId: ---------------> " + orderId);
            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addOrderItems(List<CartItem> cartItems, String sessionId) {
        try {
            Integer orderId = getOrderIdBySessionId(sessionId);
            for (CartItem cartItem: cartItems
                 ) {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
                String sqlAddCartItem = "INSERT INTO order_item (productId, orderId, price, discount, quantity, content) VALUE (?,?,?,?,?,?)";
                PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
                statement.setInt(1, cartItem.getProductId());
                statement.setInt(2, orderId);
                statement.setDouble(3, cartItem.getPrice());
                statement.setDouble(4, cartItem.getDiscount());
                statement.setInt(5, cartItem.getQuantity());
                statement.setString(6, cartItem.getContent());
                statement.executeUpdate();
                statement.close();
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End addToCart
    }
}
