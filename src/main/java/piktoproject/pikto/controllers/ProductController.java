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
@SessionAttributes({"userId","cartId"})
public class ProductController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ShoppingService productService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String indexRedirect(Model model, HttpServletRequest request) {
        return "redirect:/Index";
    }

    @RequestMapping("/Index")
    public String getIndex(Model model, HttpServletRequest request) {
        model.addAttribute("allProducts", adminService.getAllProducts());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();
        model.addAttribute("sessionId", session.getId());

        System.out.println(session.getId());
        if (auth.getPrincipal() != "anonymousUser") {
            System.out.println("User is logged in as: " + auth.getPrincipal());
            User user = (User) session.getAttribute("userData");
            model.addAttribute("loggedIn", "loggedintrue");
            model.addAttribute("userData", user);
            return "Frontend/Main/Index";
        }
        User user = new User();
        user.setUserId(0);
        model.addAttribute("userData", user);
        System.out.println("User is not logged in");
        model.addAttribute("loggedIn", "loggedinfalse");
        return "Frontend/Main/Index";
    }

    @RequestMapping("/Index/{productId}")
    public String getProductPage(Model model, @PathVariable Integer productId, HttpServletRequest request) {
        model.addAttribute("Product", adminService.getProduct(productId));
        model.addAttribute("Reviews", userService.getAllProductReviews(productId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();
        model.addAttribute("sessionId", session.getId());
        System.out.println(session.getId());
        if (auth.getPrincipal() != "anonymousUser") {
            System.out.println("User is logged in as: " + auth.getPrincipal());
            User user = (User) session.getAttribute("userData");
            model.addAttribute("loggedIn", "loggedintrue");
            model.addAttribute("userData", user);
            return "Frontend/Main/ProductPage";
        }
        User user = new User();
        user.setUserId(0);
        model.addAttribute("userData", user);
        System.out.println("User is not logged in");
        model.addAttribute("loggedIn", "loggedinfalse");
        return "Frontend/Main/ProductPage";
    }

    @RequestMapping("/Index/Checkout/{SessionId}")
    public String getAnonymousCart(Model model, @PathVariable String SessionId) {
        Cart cart = productService.getCart(SessionId);
        System.out.println(cart.getCartId());
        model.addAttribute("userCart", productService.getAllCartItems(cart));
        return "Frontend/Main/Checkout";
    }

    @RequestMapping("/Index/Search/{SearchWord}")
    public String getProductPage(Model model, @PathVariable String SearchWord) {
        model.addAttribute("Product", adminService.getAllProductsBySearch(SearchWord));
        System.out.println(adminService.getAllProductsBySearch(SearchWord));
        return "Frontend/Main/SearchPage";
    }

    @RequestMapping("/Index/Category/{categoryId}")
    public String getCategoryPage(Model model, @PathVariable int categoryId, HttpServletRequest request) {
        model.addAttribute("CategoryItems", adminService.getAllProductsByCategory(categoryId));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();
        model.addAttribute("sessionId", session.getId());

        System.out.println(session.getId());
        if (auth.getPrincipal() != "anonymousUser") {
            System.out.println("User is logged in as: " + auth.getPrincipal());
            User user = (User) session.getAttribute("userData");
            model.addAttribute("loggedIn", "loggedintrue");
            model.addAttribute("userData", user);
        } else {
            User user = new User();
            user.setUserId(0);
            model.addAttribute("userData", user);
            System.out.println("User is not logged in");
            model.addAttribute("loggedIn", "loggedinfalse");
        }

        if (categoryId == 1) {
            return "Frontend/Main/Backgrounds";

        } else if (categoryId == 2) {
            return "Frontend/Main/Stockphotos";

        } else if (categoryId == 3) {
            return "Frontend/Main/Posters";

        }
        return null;
    }
}
