package piktoproject.pikto.repositorys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.Cart;
import piktoproject.pikto.models.CartItem;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.models.Transaction;

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

    }

    @Override
    public void deleteFromCart(CartItem cartItem) {

    }

    @Override
    public Cart getCart(int sessionId) {
        return null;
    }

    @Override
    public void deleteCart(Cart cart) {

    }

    @Override
    public Order createOrder(Cart cart) {
        return null;
    }

    @Override
    public Transaction createTransaction(Order order) {
        return null;
    }
}
