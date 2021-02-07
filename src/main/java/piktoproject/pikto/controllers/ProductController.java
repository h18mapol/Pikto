package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.services.ShoppingService;

@Controller
@SessionAttributes("userName")
public class ProductController {

 @Autowired
 private ShoppingService productService;

    @RequestMapping("/hello")
    public String loginUser(Model model){
        String userName = "Marcus";
        model.addAttribute("userName", userName);
        return "hello";
    }
}
