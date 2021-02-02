package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.UserService;

@Controller
@SessionAttributes("userName")
public class AdminController {
 @Autowired
    private AdminService adminService;
    private UserService userService;
  
    @RequestMapping("/Admin")
    public String getAdminPage(Model model){
    model.addAttribute("allProducts",adminService.getAllProducts());
       return "Frontend/Admin/Admin";
    }

     @RequestMapping("/Admin/Products")
    public String getAllProducts(Model model){
    model.addAttribute("allProducts",adminService.getAllProducts());
       return "Frontend/Admin/Products";
    }   
     @RequestMapping("/Admin/Users")
    public String getAllUsers(Model model){
    model.addAttribute("allUsers",adminService.getAllUsers());
       return "Frontend/Admin/Users";
        
    }   
      
    @RequestMapping("/Admin/{userId}")
    public String getUserPage(Model model, @PathVariable Integer userId){
       // model.addAttribute("userData",userService.getUserById(userId));
        model.addAttribute("userProducts",userService.getAllProducts(userId));
        model.addAttribute("userReviews",userService.getAllReviews(userId));
       // model.addAttribute("userOrders",userService.getAllOrders(userId));
        return "Frontend/Admin/IndividualUser";
    }

    
      @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
    model.addAttribute("allOrders", adminService.getAllOrders());
       return "Frontend/Admin/Orders";
        
    }  
}
