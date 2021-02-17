package piktoproject.pikto.repositorys;

import java.sql.*;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ShoppingFunctions implements IShoppingFunctions {
     private Connection con;
     
    @Override
    public List<CartItem> getAllCartItems(Cart cart) {
       List <CartItem> cartList=new ArrayList<>();
        try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlSelectOrders="SELECT * FROM cart_item WHERE cartId ="+cart.getCartId();
            ResultSet resultset=statement.executeQuery(sqlSelectOrders);
            while (resultset.next()){
                CartItem cartitem=new CartItem();
                cartitem.setCartItemId(resultset.getInt(1));
                cartitem.setProductId(resultset.getInt(2));
                cartitem.setCartId(resultset.getInt(3));
                cartitem.setPrice(resultset.getFloat(4));
                cartitem.setDiscount(resultset.getInt(5));
                cartitem.setQuantity(resultset.getInt(6));
                cartitem.setCreatedAt(resultset.getString(7));
                cartitem.setContent(resultset.getString(8));
                cartList.add(cartitem);
            }
         
            resultset.close();
            statement.close();
            con.close();
            return cartList;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     } //Klar
    @Override
    public void addToCart(CartItem cartItem) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            String sqlAddCartItem = "INSERT INTO cart_item (productId, cartId, price, discount, quantity, content) VALUE (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
            statement.setInt (1, cartItem.getProductId());
            statement.setInt(2, cartItem.getCartId());
            statement.setFloat(3, cartItem.getPrice());
            statement.setFloat(4, cartItem.getDiscount());
            statement.setInt(5, cartItem.getQuantity());
            statement.setString(6, cartItem.getContent());
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End addToCart
    } //Klar
    @Override
    public void deleteFromCart(CartItem cartItem) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteCartItem = "DELETE FROM piktodb.cart_item WHERE cartItemId="+cartItem.getCartItemId();
            statement.executeUpdate(sqlDeleteCartItem);
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteFromCart
    } //Klart

    @Override
    public Cart getCart(String sessionId) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlgetCartBySession = "SELECT * FROM cart WHERE sessionId=?";
            PreparedStatement statement = con.prepareStatement(sqlgetCartBySession);
            statement.setString(1, sessionId);

            ResultSet resultSet = statement.executeQuery();
            Cart cart = new Cart();
            while (resultSet.next()) {
                cart.setCartId(resultSet.getInt("cartId"));
                cart.setUserId(resultSet.getInt("userId"));
                cart.setSessionId(resultSet.getString("sessionId")); //eventdate
                cart.setStatus(resultSet.getBoolean("status"));
                cart.setFirstName(resultSet.getString("firstName"));
                cart.setLastName(resultSet.getString("lastName"));
                cart.setMobile(resultSet.getString("mobile"));
                cart.setEmail(resultSet.getString("email"));
                cart.setAddress(resultSet.getString("address"));
                cart.setCity(resultSet.getString("city"));
                cart.setCreatedAt(resultSet.getString("createdAt"));
                cart.setContent(resultSet.getString("content"));
            } //End while
            resultSet.close();
            statement.close();
            con.close();
            return cart;
        } //end try
        catch(SQLException ex){
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End GetCart
        return null;
    } //Klar
    @Override
    public void deleteCart(Cart cart) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteCart = "DELETE FROM piktodb.cart WHERE cartId="+cart.getCartId();
            statement.executeUpdate(sqlDeleteCart);
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteCart
    } //Klar

    @Override
    public void createOrder(Cart cart, User user) {
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            String sqlAddCartItem = "INSERT INTO `order` (userId, sessionId, status, subTotal, itemDiscount, tax, shipping, total, promo, discount, grandTotal, firstName, lastName, mobile, email, address, city, content) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
            statement.setInt (1, cart.getUserId());
            statement.setString(2, cart.getSessionId());
            statement.setInt(3, 0); // Status = Not delivered
            statement.setInt(4, 0); //subTotal
            statement.setInt(5, 0); //itemDiscount
            statement.setInt(6, 0); //tax
            statement.setInt (7, 0); //shipping
            statement.setInt(8, 0); //total
            statement.setInt(9, 0); //promo
            statement.setInt(10, 0); //discount
            statement.setInt(11, 0); //Grand total
            statement.setString(12, cart.getFirstName());
            statement.setString (13, cart.getLastName());
            statement.setString(14, cart.getMobile());
            statement.setString(15, cart.getEmail());
            statement.setString(16, cart.getAddress());
            statement.setString(17, cart.getCity());
            statement.setString(18, cart.getContent());
            statement.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End CreateOrder;
    } //Behöver function för att beräknat total kostnad

    @Override
    public Transaction createTransaction(Order order) {
        return null;
    }
}
