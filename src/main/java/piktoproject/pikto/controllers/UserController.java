package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import piktoproject.pikto.services.UserService;

@Controller
@SessionAttributes("userName")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/User/{userId}")
    public String getAdminPage(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",userService.getAllProducts(userId));
        System.out.println(userService.getAllProducts(userId).get(0).getProductId()); //Funkar
        System.out.println(userService.getAllProducts(userId).get(1).getProductId()); //Funkar
        return "urlview";
    }

    @RequestMapping("/User/Product/{productId}")
    public String getAllProducts(Model model, @PathVariable Integer productId){
        model.addAttribute("product",userService.getProduct(productId));
        System.out.println(userService.getProduct(productId).getProductId());
        return "user_getproduct";
    }





}
