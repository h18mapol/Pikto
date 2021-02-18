package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.User;
import piktoproject.pikto.models.Cart;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.ShoppingService;
import piktoproject.pikto.services.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("userName")
public class ProductController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ShoppingService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/User/Checkout/{userId}")

    public String checkoutUser(Model model, @PathVariable Integer userId) {
        Cart cart = new Cart();
        cart.setCartId(1);
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("userCart", productService.getAllCartItems(cart));
        return "Frontend/User/Checkout";
    }

    @RequestMapping("/Index")
    public String getIndex(Model model, HttpServletRequest request) {
        model.addAttribute("allProducts", adminService.getAllProducts());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userData");
            model.addAttribute("userData", user);
            return "Frontend/Main/Index";
        }
        System.out.println("User is not logged in");
        model.addAttribute("userData", "null");
        return "Frontend/Main/Index";
    }

    @RequestMapping("/Index/{productId}")
    public String getProductPage(Model model, @PathVariable Integer productId) {
        model.addAttribute("Product", adminService.getProduct(productId));
        model.addAttribute("Reviews", userService.getAllProductReviews(productId));
        return "Frontend/Main/ProductPage";
    }

    @RequestMapping("/Index/Search/{SearchWord}")
    public String getProductPage(Model model, @PathVariable String SearchWord) {
        model.addAttribute("Product", adminService.getAllProductsBySearch(SearchWord));
        System.out.println(adminService.getAllProductsBySearch(SearchWord));
        return "Frontend/Main/SearchPage";
    }



}
