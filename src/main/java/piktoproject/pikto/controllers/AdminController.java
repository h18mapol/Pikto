package piktoproject.pikto.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.User;
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
          @RequestMapping(path="/Admin/addProduct", method={RequestMethod.POST,RequestMethod.PUT})
    public String addRequestTeam(@ModelAttribute ("Product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.addProduct(product);
       return "redirect:/Admin";
        
    }
         @RequestMapping(path="/Admin/addUser", method={RequestMethod.POST,RequestMethod.PUT})
    public String addRequestTeam(@ModelAttribute ("User")User user,@RequestParam Map<String, String> allRequestParams){
        userService.addUser(user);
       return "redirect:/Admin";
        
    }
      
    @RequestMapping("/Admin/{userId}")
    public String getUserPage(Model model, @PathVariable Integer userId){

        model.addAttribute("userData",adminService.getUser(userId));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(userId));
        model.addAttribute("userReviews",adminService.getAllReviewsById(userId));
        model.addAttribute("userOrders",adminService.getAllOrdersById(userId));

        return "Frontend/Admin/IndividualUser";
    }

    
      @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
    model.addAttribute("allOrders", adminService.getAllOrders());
       return "Frontend/Admin/Orders";
    }  
}
