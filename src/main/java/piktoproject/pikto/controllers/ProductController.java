package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import piktoproject.pikto.service.AdminService;

@Controller
@SessionAttributes("userName")
public class ProductController {
 @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String loginUser(Model model){
        String userName = "Marcus";
        model.addAttribute("userName", userName);

        return "urlview2";
    }

    @RequestMapping("/Admin")
    public String getAdminPage(Model model){
    model.addAttribute("allProducts","Snoppen");
       return "Frontend/Admin/Admin";
    }    
     @RequestMapping("/Admin/Products")
    public String getAllProducts(Model model){
    model.addAttribute("allProducts","Snoppen");
       return "Frontend/Admin/Products";
        
    }   
     @RequestMapping("/Admin/Users")
    public String getAllUsers(Model model){
    model.addAttribute("allProducts","Snoppen");
       return "Frontend/Admin/Users";
        
    }   
    
      @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
    model.addAttribute("allProducts","Snoppen");
       return "Frontend/Admin/Orders";
        
    }  
}
