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
    public String getAllUserProducts(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",userService.getAllUserProducts(userId));
        System.out.println(userService.getAllUserReviews(userId).get(0).getProductId()); //Funkar
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
    public String getAllUserReviews(Model model, @PathVariable Integer userId){
        model.addAttribute("reviews",userService.getAllUserReviews(userId));
        System.out.println(userService.getAllUserReviews(userId).get(0).getTitle());
        return "getreview";
    }

    @RequestMapping("/User/{userId}/Orders")
    public String getAllUserOrders(Model model, @PathVariable Integer userId){
        model.addAttribute("orders",userService.getAllUserOrders(userId));
        System.out.println(userService.getAllUserOrders(userId).get(0).getFirstName());
        return "getorders";
    }










}
