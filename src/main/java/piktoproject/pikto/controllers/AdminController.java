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
 @Autowired
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
<<<<<<< HEAD
      model.addAttribute("userData",adminService.getUser(userId));
      model.addAttribute("userProducts",userService.getAllProducts(userId));
       model.addAttribute("userReviews",userService.getAllReviews(userId));
       model.addAttribute("userOrders",userService.getAllOrders(userId));
=======
        model.addAttribute("userData",adminService.getUser(userId));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(userId));
        model.addAttribute("userReviews",adminService.getAllReviewsById(userId));
        model.addAttribute("userOrders",adminService.getAllOrdersById(userId));
>>>>>>> e95576719a042b0c9b2f99a7111925de1cd0d5cd
        return "Frontend/Admin/IndividualUser";
    }

    
      @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
    model.addAttribute("allOrders", adminService.getAllOrders());
       return "Frontend/Admin/Orders";
    }  
}
