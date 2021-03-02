package piktoproject.pikto.repositorys;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public void createCart(String sessionId, User user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddUser = "call piktodb.add_cart(?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddUser);
            statement.setInt(1, user.getUserId());
            statement.setString(2, sessionId);
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getMobileNr());
            statement.setString(6, user.getEmail());
            statement.executeQuery();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }//End addUser
    }

    @Override
    public List<CartItem> getAllCartItems(Cart cart) {
        List<CartItem> cartList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlSelectOrders = "SELECT * FROM cart_item WHERE cartId =" + cart.getCartId();
            ResultSet resultset = statement.executeQuery(sqlSelectOrders);
            while (resultset.next()) {
                CartItem cartitem = new CartItem();
                cartitem.setCartItemId(resultset.getInt(1));
                cartitem.setProductId(resultset.getInt(2));
                cartitem.setCartId(resultset.getInt(3));
                cartitem.setPrice(resultset.getDouble(4));
                cartitem.setDiscount(resultset.getDouble(5));
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
    public List<CartItemDTO> getAllCartItemsDTO(Cart cart) {
        List<CartItemDTO> cartListDTO = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlSelectOrders = "SELECT cart_item.*, product.productUrl, product.title\n" +
                    "FROM cart_item\n" +
                    "LEFT JOIN product ON cart_item.productId = product.productId\n" +
                    "WHERE cartId = " + cart.getCartId();
            ResultSet resultset = statement.executeQuery(sqlSelectOrders);
            while (resultset.next()) {
                CartItemDTO cartitemDTO = new CartItemDTO();
                cartitemDTO.setCartItemId(resultset.getInt(1));
                cartitemDTO.setProductId(resultset.getInt(2));
                cartitemDTO.setCartId(resultset.getInt(3));
                cartitemDTO.setPrice(resultset.getDouble(4));
                cartitemDTO.setDiscount(resultset.getDouble(5));
                cartitemDTO.setQuantity(resultset.getInt(6));
                cartitemDTO.setCreatedAt(resultset.getString(7));
                cartitemDTO.setContent(resultset.getString(8));
                cartitemDTO.setProductUrl(resultset.getString(9));
                cartitemDTO.setTitle(resultset.getString(10));
                cartListDTO.add(cartitemDTO);
            }
            resultset.close();
            statement.close();
            con.close();
            return cartListDTO;
        } catch (SQLException ex) {
            Logger.getLogger(AdminCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void addToCart(CartItem cartItem) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddCartItem = "INSERT INTO cart_item (productId, cartId, price, discount, quantity, content) VALUE (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
            statement.setInt(1, cartItem.getProductId());
            statement.setInt(2, cartItem.getCartId());
            statement.setDouble(3, cartItem.getPrice());
            statement.setDouble(4, cartItem.getDiscount());
            statement.setInt(5, cartItem.getQuantity());
            statement.setString(6, cartItem.getContent());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End addToCart
    } //Klar

    @Override
    public void deleteCartItem(Integer cartItemId) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            System.out.println("DELETE From Cart");
            String sqlDeleteCartItem = "DELETE FROM piktodb.cart_item WHERE cartItemId=" + cartItemId;
            statement.executeUpdate(sqlDeleteCartItem);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteCartItem
    } //Klart

    @Override
    public Cart getCart(String sessionId) {
        try {
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
                cart.setMobileNr(resultSet.getString("mobileNr"));
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
        catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End GetCart
        return null;
    } //Klar

    public void emptyCart(Cart cart){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteCartItem = "DELETE FROM piktodb.cart_item WHERE cartId=" + cart.getCartId();
            statement.executeUpdate(sqlDeleteCartItem);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteFromCart
    }

    @Override
    public void deleteCart(Cart cart) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            Statement statement = con.createStatement();
            statement = con.createStatement();
            String sqlDeleteCart = "DELETE FROM piktodb.cart WHERE cartId=" + cart.getCartId();
            statement.executeUpdate(sqlDeleteCart);
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End deleteCart
    } //Klar

    @Override
    public void createOrder(Cart cart, User user) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddCartItem = "INSERT INTO `order` (userId, sessionId, status, subTotal, itemDiscount, tax, shipping, total, promo, discount, grandTotal, firstName, lastName, mobile, email, address, city, content) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
            statement.setInt(1, cart.getUserId());
            statement.setString(2, cart.getSessionId());
            statement.setInt(3, 0); // Status = Not delivered
            statement.setInt(4, 0); //subTotal
            statement.setInt(5, 0); //itemDiscount
            statement.setInt(6, 0); //tax
            statement.setInt(7, 0); //shipping
            statement.setInt(8, 0); //total
            statement.setInt(9, 0); //promo
            statement.setInt(10, 0); //discount
            statement.setInt(11, 0); //Grand total
            statement.setString(12, cart.getFirstName());
            statement.setString(13, cart.getLastName());
            statement.setString(14, cart.getMobileNr());
            statement.setString(15, cart.getEmail());
            statement.setString(16, cart.getAddress());
            statement.setString(17, cart.getCity());
            statement.setString(18, cart.getContent());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End CreateOrder;
    } //Behöver function för att beräknat total kostnad

    @Override
    public void createOrderPaypal(Order order) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC", "root", "");
            String sqlAddCartItem = "INSERT INTO `order` (userId, sessionId, status, subTotal, itemDiscount, tax, shipping, total, promo, discount, grandTotal, firstName, lastName, mobile, email, address, city, content) VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlAddCartItem);
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getSessionId());
            statement.setInt(3, order.getStatus()); // Status = Not delivered
            statement.setDouble(4, order.getSubTotal()); //subTotal
            statement.setDouble(5, order.getItemDiscount()); //itemDiscount
            statement.setDouble(6, order.getTax()); //tax
            statement.setDouble(7, order.getShipping()); //shipping
            statement.setDouble(8, order.getTotal()); //total
            statement.setString(9, order.getPromo()); //promo
            statement.setDouble(10, order.getDiscount()); //discount
            statement.setDouble(11, order.getGrandTotal()); //Grand total
            statement.setString(12, order.getFirstName());
            statement.setString(13, order.getLastName());
            statement.setString(14, order.getMobile());
            statement.setString(15, order.getEmail());
            statement.setString(16, order.getAddress());
            statement.setString(17, order.getCity());
            statement.setString(18, order.getContent());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }//End CreateOrder;
    }

    @Override
    public Transaction createTransaction(Order order) {
        return null;
    }

    //Helper function
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
