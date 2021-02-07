package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping("/login")
    public String loginUser(Model model){
        String userName = "Marcus";
        model.addAttribute("userName", userName);
        return "urlview2";
    }
    
    
    @RequestMapping("/User/Checkout/{userId}")
    public String checkoutUser(Model model, @PathVariable Integer userId){
        model.addAttribute("user",userService.getUserById(userId));
        return "Frontend/User/Checkout";
    }
    
       @RequestMapping("/")
    public String getIndex(Model model){
        model.addAttribute("allProducts",adminService.getAllProducts());
   
        return "Frontend/Main/Index";
    }
    
     @RequestMapping("/Index/{productId}")
    public String getProductPage(Model model, @PathVariable Integer productId){
        model.addAttribute("Product",adminService.getProduct(productId));
        return "Frontend/Main/ProductPage";
    }
    
    
}
