package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.Product_review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class UserCrud implements IUserCrud {
    private Connection con;

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
}
