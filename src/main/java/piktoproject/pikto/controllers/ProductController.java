package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.services.AdminService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import piktoproject.pikto.services.AdminService;

@Controller
@SessionAttributes("userName")
public class ProductController {
 @Autowired

    @RequestMapping("/login")
    public String loginUser(Model model){
        String userName = "Marcus";
        model.addAttribute("userName", userName);
        return "urlview2";
    }

}
