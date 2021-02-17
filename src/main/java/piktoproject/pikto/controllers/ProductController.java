package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.Cart;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.ShoppingService;
import piktoproject.pikto.services.UserService;

@Controller
@SessionAttributes("userName")
public class ProductController {
    
     @Autowired
 private AdminService adminService;

 @Autowired
 private ShoppingService productService;

@Autowired
  private UserService userService;
    
    
    @RequestMapping("/User/Checkout/{userId}")
    public String checkoutUser(Model model, @PathVariable Integer userId){
          Cart cart=new Cart();
        cart.setCartId(1);
        model.addAttribute("user",userService.getUserById(userId));
          model.addAttribute("userCart",productService.getAllCartItems(cart));

        return "Frontend/User/Checkout";
    }

      @RequestMapping("/Index")
    public String getIndex(Model model){
        model.addAttribute("allProducts",adminService.getAllProducts());
        return "Frontend/Main/Index";
    }
    
     @RequestMapping("/Index/{productId}")
    public String getProductPage(Model model, @PathVariable Integer productId){
        model.addAttribute("Product",adminService.getProduct(productId));
        model.addAttribute("Reviews",userService.getAllProductReviews(productId));
        return "Frontend/Main/ProductPage";
    }
    
     @RequestMapping("/Index/Search/{SearchWord}")
    public String getProductPage(Model model, @PathVariable String SearchWord){
        model.addAttribute("Product",adminService.getAllProductsBySearch(SearchWord));
         System.out.println(adminService.getAllProductsBySearch(SearchWord));
        return "Frontend/Main/SearchPage";
    }
    
    
}
