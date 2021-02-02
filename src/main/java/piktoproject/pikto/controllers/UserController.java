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

        System.out.println(userService.getAllProducts(userId).get(0).getProductId());
        System.out.println(userService.getAllProducts(userId).get(1).getProductId());
        return "urlview";
    }

    @RequestMapping("/User/Products/{userId}")
    public String getAllProducts(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",userService.getAllProducts(userId));
        return "urlview";
    }
    @RequestMapping("/User/Users")
    public String getAllUsers(Model model){
        model.addAttribute("allProducts","Snoppen");
        return "Frontend/Admin/Users";
    }

    @RequestMapping("/User/Orders")
    public String getAllOrders(Model model){
        model.addAttribute("allProducts","Snoppen");
        return "Frontend/Admin/Orders";

    }
}
