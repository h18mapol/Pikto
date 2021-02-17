package piktoproject.pikto.controllers;

import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.models.User;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.UserService;


import javax.management.relation.Role;

@Controller
@SessionAttributes("userData")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @RequestMapping("/Admin")
    public String getAdminPageWithId(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("allProducts",adminService.getAllProducts());
        model.addAttribute("userData",adminService.getUserByEmail(auth.getName()));
        System.out.println(auth.getName());
        return "Frontend/Admin/Admin";
    }
    @RequestMapping("/Admin/{userId}")
    public String getAdminPageWithId(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",adminService.getAllProducts());
        model.addAttribute("userData",userService.getUserById(userId));
        return "Frontend/Admin/Admin";
    }
    @RequestMapping("/Admin/Products")
    public String getAllProducts(Model model){
        model.addAttribute("allProducts",adminService.getAllProducts());
        System.out.println(adminService.getAllProducts().get(0).getContent());
        return "Frontend/Admin/Products";
    }
    @RequestMapping("/Admin/Users")
    public String getAllUsers(Model model){
        model.addAttribute("allUsers",adminService.getAllUsers());
        return "Frontend/Admin/Users";
    }
    @RequestMapping("/Admin/Reviews")
    public String getAllReviews(Model model){
        model.addAttribute("allReviews",adminService.getAllReviews());
        return "Frontend/Admin/Reviews";
    }
    @RequestMapping("/Admin/User/{userId}")
    public String getUserPage(Model model, @PathVariable Integer userId){
        model.addAttribute("userData",adminService.getUser(userId));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(userId));
        model.addAttribute("userReviews",adminService.getAllReviewsById(userId));
        model.addAttribute("userOrders",adminService.getAllOrdersById(userId));
        return "Frontend/Admin/IndividualUser";
    }

    @RequestMapping(path="/Admin/addProduct", method={RequestMethod.POST})
    public String addProduct(@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){

        System.out.println(product.getTitle());
        System.out.println(product.getCategoryId());
        System.out.println(product.getProductUrl());
        System.out.println(product.getPrice());
        System.out.println(product.getContent());
        System.out.println(product.getDiscount());
        System.out.println(product.getPublishedAt());
        System.out.println(product.getType());
        System.out.println(product.getSummary());
        System.out.println(product.getProductId());
        System.out.println(product.getUserId());
        userService.addProduct(product);
        return "redirect:/Admin";

    }
    @RequestMapping(path="/Admin/addUser", method={RequestMethod.POST,RequestMethod.PUT})
    public String addUser(@ModelAttribute ("User")User user,@RequestParam Map<String, String> allRequestParams){
        userService.addUser(user);
        return "redirect:/Admin";
    }

    @RequestMapping(path="/Admin/updateUser", method={RequestMethod.POST})
    public String updateUser(@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.addProduct(product);
        return "redirect:/Admin/Users";
    }
    @RequestMapping(path="/Admin/updateProduct", method={RequestMethod.POST})
    public String updateProduct(@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.updateProduct(product);
        return "redirect:/Admin/Products";
    }




    @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
        model.addAttribute("allOrders", adminService.getAllOrders());
        return "Frontend/Admin/Orders";
    }

}

