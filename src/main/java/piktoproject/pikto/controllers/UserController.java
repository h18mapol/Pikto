package piktoproject.pikto.controllers;

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

import java.util.Map;

@Controller
@SessionAttributes("userId")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    @RequestMapping("/User/{userId}/Products")
    public String getAllUserProducts(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",userService.getAllUserProducts(userId));
        System.out.println(userService.getAllUserReviews(userId).get(0).getProductId()); //Funkar
        return "urlview";
    }

    @RequestMapping("/Category/{categoryId}/Products")
    public String getAllCategoryProducts(Model model, @PathVariable Integer categoryId){
        model.addAttribute("allProducts",userService.getAllCategoryProducts(categoryId));
        System.out.println(userService.getAllUserReviews(categoryId).get(0).getProductId()); //Funkar
        return "getcategories";
    }

    @RequestMapping("/User/Product/{productId}")
    public String getProduct(Model model, @PathVariable Integer productId){
        model.addAttribute("product",userService.getProduct(productId));
        System.out.println(userService.getProduct(productId).getCategoryId());
        return "getproduct";
    }

    @RequestMapping("/User")
    public String getUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = adminService.getUserByEmail(auth.getName());
        model.addAttribute("userData", user);
        model.addAttribute("userOrders",userService.getAllUserOrders(user.getUserId()));
        model.addAttribute("userProducts",userService.getAllUserProducts(user.getUserId()));
        model.addAttribute("userReviews",userService.getAllUserReviews(user.getUserId()));
        return "Frontend/User/userPage";
    }

    @RequestMapping("/User/{userId}")
    public String getUserById(Model model, @PathVariable Integer userId){
            model.addAttribute("userData",userService.getUserById(userId));
        model.addAttribute("userOrders",userService.getAllUserOrders(userId));
       model.addAttribute("userProducts",userService.getAllUserProducts(userId));
       model.addAttribute("userReviews",userService.getAllUserReviews(userId));

        return "Frontend/User/userPage";
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

    @PostMapping(path = "/User/Add/Product")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam Map<String, String> allRequestParams, @PathVariable Integer productId ){
        userService.addProduct(product);
        return "redirect:/getproduct";
    }








}
