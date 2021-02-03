package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.UserService;

@Controller
@SessionAttributes("userName")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/User/{userId}/Products")
    public String getUserAllProducts(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",userService.getAllProducts(userId));
        System.out.println(userService.getAllProducts(userId).get(0).getProductId()); //Funkar
        return "urlview";
    }

    @RequestMapping("/User/Product/{productId}")
    public String getProduct(Model model, @PathVariable Integer productId){
        model.addAttribute("product",userService.getProduct(productId));
        System.out.println(userService.getProduct(productId).getProductId());
        return "getproduct";
    }

    @RequestMapping("/User/{userId}")
    public String getUserById(Model model, @PathVariable Integer userId){
        model.addAttribute("user",userService.getUserById(userId));
        System.out.println(userService.getUserById(userId).getFirstName());
        return "getuser";
    }

    @RequestMapping("/User/{userId}/Reviews")
    public String getAllReviews(Model model, @PathVariable Integer userId){
        model.addAttribute("reviews",userService.getAllReviews(userId));
        System.out.println(userService.getAllReviews(userId).get(0).getTitle());
        return "getreview";
    }

    @RequestMapping("/User/{userId}/Orders")
    public String getAllOrders(Model model, @PathVariable Integer userId){
        model.addAttribute("orders",userService.getAllOrders(userId));
        System.out.println(userService.getAllOrders(userId).get(0).getFirstName());
        return "getorders";
    }









}
