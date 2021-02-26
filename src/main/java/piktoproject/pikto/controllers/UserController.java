package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.*;

import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.UserService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import piktoproject.pikto.services.ShoppingService;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes({"userId", "orderData"})
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;
    @Autowired
    OAuth2AuthorizedClientService authclientService;
    @Autowired
    private ShoppingService shoppingService;


    @RequestMapping("/User")
    public String getUser(Model model) {
        User user = adminService.getLoggedInUser();
        model.addAttribute("userData", user);
        model.addAttribute("userOrders", userService.getAllUserOrders(user.getUserId()));
        model.addAttribute("userProducts", userService.getAllUserProducts(user.getUserId()));
        model.addAttribute("userReviews", userService.getAllUserReviews(user.getUserId()));
        return "Frontend/User/userPage";
    }

    @RequestMapping("/User/Checkout/Status/{status}")
    public String createOrder(Model model, HttpServletRequest request, @PathVariable String status) {
        Order order = (Order) request.getSession().getAttribute("orderData");
        if(status.equalsIgnoreCase("COMPLETED")){
            order.setStatus(2); //Set as completed
            shoppingService.createOrderPaypal(order);
            //Empty cart
            ///shoppingService.emptyCart(shoppingService.getCart(order.getSessionId()));
            System.out.println("order created: " + order.getSessionId());
        return "Frontend/Main/Index";
        }
        System.out.println("Payment Denied for: " + order.getSessionId());
        return "Frontend/User/Payment";
    }

    @RequestMapping("/User/Checkout")
    public String checkoutUser(Model model) {
        User user = adminService.getLoggedInUser();
        Cart cart = shoppingService.getCart("1");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        double subTotal = 0;
        double itemDiscount = 0;
        double tax = 0.30;
        double shipping = 30;
        double total = 0;
        double discount = 0; //Promo Discount!
        double grandTotal = 0;
        int itemcount = 0;
        for (CartItem cartItem: shoppingService.getAllCartItems(cart)
        ) {
            itemcount += 1;
            double cartItemCost = cartItem.getPrice()*cartItem.getQuantity();
            subTotal += cartItemCost;
            itemDiscount += (cartItemCost * cartItem.getDiscount());
            System.out.println(itemcount + " costs --> Discount: " + itemDiscount + " : --> " + cartItem.getDiscount() );
        }
        total = (subTotal * (1+tax)) + shipping;
        grandTotal = ((total* (1 - discount)) - itemDiscount);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("itemCounter", itemcount);
        model.addAttribute("total", round(total, 2));
        model.addAttribute("itemDiscount", round(itemDiscount, 2));
        model.addAttribute("shipping", shipping);
        model.addAttribute("subTotal", round(subTotal, 2));
        model.addAttribute("userData", user);
        model.addAttribute("userCart", shoppingService.getAllCartItemsDTO(cart));
        System.out.println(shoppingService.getAllCartItemsDTO(cart).size());
        return "Frontend/User/Checkout";
    }

    @RequestMapping(path="/User/createOrder", method={RequestMethod.POST})
    public String paymentUser(Model model, HttpServletRequest request, @ModelAttribute ("order") Order order, @RequestParam Map<String, String> allRequestParams) {
        System.out.println(order.getCity());
        System.out.println(order.getAddress());
        System.out.println(order.getFirstName());
        System.out.println(order.getEmail());
        System.out.println(order.getUserId());
        System.out.println(order.getZip());
        System.out.println(order.getCountry());
        User user = adminService.getLoggedInUser();
        Cart cart = shoppingService.getCart("1");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        double subTotal = 0;
        double itemDiscount = 0;
        double tax = 0.30;
        double shipping = 30;
        double total = 0;
        double discount = 0; //Promo Discount!
        double grandTotal = 0;
        int itemcount = 0;
        for (CartItem cartItem: shoppingService.getAllCartItems(cart)
        ) {
            itemcount += 1;
            double cartItemCost = cartItem.getPrice()*cartItem.getQuantity();
            subTotal += cartItemCost;
            itemDiscount += (cartItemCost * cartItem.getDiscount());
            //System.out.println(itemcount + " costs --> Discount: " + itemDiscount + " : --> " + cartItem.getDiscount() );
        }
        total = (subTotal * (1+tax)) + shipping;
        grandTotal = ((total* (1 - discount)) - itemDiscount);
        model.addAttribute("itemCounter", itemcount);
        model.addAttribute("total", round(total, 2));
        model.addAttribute("itemDiscount", round(itemDiscount, 2));
        model.addAttribute("shipping", shipping);
        model.addAttribute("subTotal", round(subTotal, 2));
        model.addAttribute("userData", user);
        model.addAttribute("userCart", shoppingService.getAllCartItemsDTO(cart));
        model.addAttribute("grandTotal", round(grandTotal,2));
        order.setMobile(user.getMobileNr());
        order.setSessionId(request.getSession().getId());
        order.setStatus(0);
        order.setSubTotal(subTotal);
        order.setItemDiscount(itemDiscount);
        order.setTax(tax);
        order.setShipping(shipping);
        order.setTotal(total);
        order.setPromo("");
        order.setDiscount(discount);
        order.setGrandTotal(grandTotal);
        order.setMobile(user.getMobileNr());
        request.getSession().setAttribute("orderData", order);
        return "Frontend/User/Payment";
    }


    @RequestMapping("/User/{userId}")
    public String getUserById(Model model, @PathVariable Integer userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = adminService.getUserByEmail(auth.getName());
        if (user.getUserId() == userId) {
            model.addAttribute("userData", user);
            model.addAttribute("userOrders", userService.getAllUserOrders(userId));
            model.addAttribute("userProducts", userService.getAllUserProducts(userId));
            model.addAttribute("userReviews", userService.getAllUserReviews(userId));
            return "Frontend/User/userPage";
        } else {
            System.out.println("Redirect to /User");
            return "redirect:http://localhost:8888/User";
        }
    }

    /*@RequestMapping("/User/{userId}/Reviews")
    public String getAllUserReviews(Model model, @PathVariable Integer userId) {
        model.addAttribute("reviews", userService.getAllUserReviews(userId));
        System.out.println(userService.getAllUserReviews(userId).get(0).getTitle());
        return "getreview";
    }

    @RequestMapping("/User/{userId}/Products")
    public String getAllUserProducts(Model model, @PathVariable Integer userId) {
        model.addAttribute("allProducts", userService.getAllUserProducts(userId));
        System.out.println(userService.getAllUserReviews(userId).get(0).getProductId()); //Funkar
        return "urlview";
    }

    @RequestMapping("/User/Product/{productId}")
    public String getProduct(Model model, @PathVariable Integer productId) {
        model.addAttribute("product", userService.getProduct(productId));
        System.out.println(userService.getProduct(productId).getCategoryId());
        return "getproduct";
    }

    @RequestMapping("/User/{userId}/Orders")
    public String getAllUserOrders(Model model, @PathVariable Integer userId) {
        model.addAttribute("orders", userService.getAllUserOrders(userId));
        System.out.println(userService.getAllUserOrders(userId).get(0).getFirstName());
        return "getorders";
    }

    @PostMapping(path = "/User/Add/Product/{productId}")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam Map<String, String> allRequestParams, @PathVariable Integer productId) {
        userService.addProduct(product);
        return "redirect:/getproduct";
    }

    @PostMapping(path = "/User/Update/Product/{productId}")
    public String updateProduct(@ModelAttribute("product") Product product, @RequestParam Map<String, String> allRequestParams, @PathVariable Integer productId) {
        userService.updateProduct(product);
        return "redirect:/getproduct";
    }

    @RequestMapping("/Category/{categoryId}/Products")
    public String getAllCategoryProducts(Model model, @PathVariable Integer categoryId) {
        model.addAttribute("allProducts", userService.getAllCategoryProducts(categoryId));
        System.out.println(userService.getAllUserReviews(categoryId).get(0).getProductId()); //Funkar
        return "getcategories";
    }*/
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
