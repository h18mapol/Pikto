package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.services.AdminService;

@Controller
@SessionAttributes("userName")
public class ProductController {

    @Autowired
    private AdminService eventservice;

    @RequestMapping("/login")
    public String loginUser(Model model){
        String userName = "Marcus";
        model.addAttribute("userName", userName);
        eventservice.getAllProducts();
        return "./urlview2";
    }

    @RequestMapping("/product")
    public String getAllProducts(Model model1){
        model1.addAttribute("attribute", "product");
        return "urlview";
    }
}
