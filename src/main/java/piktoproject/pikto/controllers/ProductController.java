package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.*;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.ShoppingService;
import piktoproject.pikto.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static piktoproject.pikto.controllers.UserController.round;

@Controller
@SessionAttributes({"userId","cartId"})
public class ProductController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ShoppingService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingService shoppingService;

    @RequestMapping("/")
    public String indexRedirect(Model model, HttpServletRequest request) {
        return "redirect:/Index";
    }

    @RequestMapping("/Index")
    public String getIndex(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Cart cart = shoppingService.getCart(session.getId());
        model.addAttribute("allProducts", adminService.getAllProducts());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("sessionId", session.getId());

        System.out.println(session.getId());
        if (auth.getPrincipal() != "anonymousUser") {
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
        if (cart == null){
            assert cart != null;
            System.out.println("empty cart! --> creating new card" + cart.getCartId());
        } else {
            System.out.println("Cart exists: ID -> " + cart.getCartId());
            shoppingService.createCart(session.getId(),user);
        }
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

    @RequestMapping("/Index/addToCart/{productId}")
    public String addToCart(Model model,@PathVariable int productId, HttpServletRequest request){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        HttpSession session = request.getSession();
        Cart cart = shoppingService.getCart(session.getId());
        Product product = adminService.getProduct(productId);
        CartItem cartItem = new CartItem();
        cartItem.setCartId(cart.getCartId());
        cartItem.setProductId(productId);
        cartItem.setPrice(product.getPrice());
        cartItem.setDiscount(product.getDiscount());
        cartItem.setQuantity(1);
        if (auth.getPrincipal() != "anonymousUser") {
            System.out.println("User is logged in as: " + auth.getPrincipal());
            User user = (User) session.getAttribute("userData");
            model.addAttribute("loggedIn", "loggedintrue");
            model.addAttribute("userData", user);
            shoppingService.addToCart(cartItem);
            return "redirect:/Index/"+productId;
        }
        User user = new User();
        user.setUserId(0);
        model.addAttribute("userData", user);
        System.out.println("User is not logged in");
        model.addAttribute("loggedIn", "loggedinfalse");
        shoppingService.addToCart(cartItem);
        System.out.println("Adding to cart: " + cartItem.getCartId());
        return "redirect:/Index/"+productId;
    }

    @RequestMapping("/Index/Checkout/{SessionId}")
    public String getAnonymousCart(Model model, @PathVariable String SessionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Order order = new Order();
        Cart cart = productService.getCart(SessionId);
        User user;
        if (auth.getPrincipal() != "anonymousUser") {
            user = adminService.getLoggedInUser();
            return "redirect:/User/Checkout";
        } else {
            user = new User();
            user.setFirstName("");
            user.setLastName("");
            user.setMobileNr("");
            user.setEmail("");
        }
        double subTotal = 0;
        double itemDiscount = 0;
        double tax = 0;
        double shipping = 30;
        double total = 0;
        double discount = 0; //Promo Discount!
        double grandTotal = 0;
        int itemcount = 0;
        if (shoppingService.getAllCartItems(cart).size() == 0){
            model.addAttribute("grandTotal", grandTotal);
            model.addAttribute("itemCounter", itemcount);
            model.addAttribute("total", round(total, 2));
            model.addAttribute("itemDiscount", round(itemDiscount, 2));
            model.addAttribute("shipping", shipping);
            model.addAttribute("subTotal", round(subTotal, 2));
            model.addAttribute("userData", user);
            model.addAttribute("userCart", shoppingService.getAllCartItemsDTO(cart));
            order.setMobile(user.getMobileNr());
            order.setSessionId(SessionId);
            order.setStatus(0);
            order.setSubTotal(round(subTotal, 2));
            order.setItemDiscount(round(itemDiscount, 2));
            order.setTax(tax);
            order.setShipping(shipping);
            order.setTotal(round(total, 2));
            order.setPromo("");
            order.setDiscount(discount);
            order.setGrandTotal(round(grandTotal,2));
            order.setMobile(user.getMobileNr());
            order.setContent("");
            model.addAttribute("orderData", order);
            return "Frontend/Main/Checkout";
        }
        for (CartItem cartItem: shoppingService.getAllCartItems(cart)
        ) {
            itemcount += 1;
            double cartItemCost = cartItem.getPrice()*cartItem.getQuantity();
            tax += cartItemCost * 0.25;
            subTotal += cartItemCost;
            itemDiscount += (cartItemCost * cartItem.getDiscount());
            System.out.println(itemcount + " costs --> Discount: " + itemDiscount + " : --> " + cartItem.getDiscount() );
        }
        total = (subTotal + tax + shipping - itemDiscount);
        grandTotal = ((total* (1 - discount)));
        order.setMobile(user.getMobileNr());
        order.setSessionId(SessionId);
        order.setStatus(0);
        order.setSubTotal(round(subTotal, 2));
        order.setItemDiscount(round(itemDiscount, 2));
        order.setTax(tax);
        order.setShipping(shipping);
        order.setTotal(round(total, 2));
        order.setPromo("");
        order.setDiscount(discount);
        order.setGrandTotal(round(grandTotal,2));
        order.setMobile(user.getMobileNr());
        order.setContent("");
        model.addAttribute("orderData", order);
        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("itemCounter", itemcount);
        model.addAttribute("total", round(total, 2));
        model.addAttribute("itemDiscount", round(itemDiscount, 2));
        model.addAttribute("shipping", shipping);
        model.addAttribute("subTotal", round(subTotal, 2));
        model.addAttribute("userData", user);
        model.addAttribute("userCart", shoppingService.getAllCartItemsDTO(cart));
        System.out.println(shoppingService.getAllCartItemsDTO(cart).size());
        return "Frontend/Main/Checkout";
    }

    @RequestMapping(path="/Index/createOrder", method={RequestMethod.POST})
    public String paymentUser(Model model, HttpServletRequest request, @ModelAttribute ("order") Order order, @RequestParam Map<String, String> allRequestParams) {
        String session = request.getSession().getId();
        Cart cart = shoppingService.getCart(session);
        User user;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() != "anonymousUser") {
            user = adminService.getLoggedInUser();
        }else {
            user = adminService.getUser(cart.getUserId());
            user.setFirstName("");
            user.setLastName("");
            user.setEmail("");
            user.setMobileNr("");
        }
        double subTotal = 0;
        double itemDiscount = 0;
        double tax = 0;
        double shipping = 30;
        double total = 0;
        double discount = 0; //Promo Discount!
        double grandTotal = 0;
        int itemcount = 0;
        for (CartItem cartItem: shoppingService.getAllCartItems(cart)
        ) {
            itemcount += 1;
            double cartItemCost = cartItem.getPrice()*cartItem.getQuantity();
            tax += cartItemCost * 0.25;
            subTotal += cartItemCost;
            itemDiscount += (cartItemCost * cartItem.getDiscount());
            System.out.println(itemcount + " costs --> Discount: " + itemDiscount + " : --> " + cartItem.getDiscount() );
        }
        total = (subTotal + tax + shipping - itemDiscount);
        grandTotal = ((total* (1 - discount)));
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
        order.setMobile("");
        order.setContent("");
        model.addAttribute("orderData", order);
        request.getSession().setAttribute("orderData", order);
        return "Frontend/Main/Payment";
    }

    @RequestMapping("Index/Checkout/Remove/{CartItemId}")
    public String removeCartItem(Model model, @PathVariable Integer CartItemId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        shoppingService.deleteCartItem(CartItemId);
        return "redirect:/Index/Checkout/"+session.getId();
    }

    @RequestMapping("Index/Payment/Remove/{CartItemId}")
    public String getAllUserReviews(Model model, @PathVariable Integer CartItemId,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("orderData");

        shoppingService.deleteCartItem(CartItemId);
        Cart cart = shoppingService.getCart(session.getId());
        User user = adminService.getLoggedInUser();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        double subTotal = 0;
        double itemDiscount = 0;
        double tax = 0;
        double shipping = 30;
        double total = 0;
        double discount = 0; //Promo Discount!
        double grandTotal = 0;
        int itemcount = 0;
        for (CartItem cartItem: shoppingService.getAllCartItems(cart)
        ) {
            itemcount += 1;
            double cartItemCost = cartItem.getPrice()*cartItem.getQuantity();
            tax += cartItemCost * 0.25;
            subTotal += cartItemCost;
            itemDiscount += (cartItemCost * cartItem.getDiscount());
            System.out.println(itemcount + " costs --> Discount: " + itemDiscount + " : --> " + cartItem.getDiscount() );
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
        order.setDiscount(discount);
        order.setGrandTotal(grandTotal);
        order.setMobile("");
        order.setContent("");
        model.addAttribute("orderData", order);
        return "Frontend/Main/Payment";
    }

    @RequestMapping("/Index/Checkout/Status/{status}")
    public String createOrder(Model model, HttpServletRequest request, @PathVariable String status) {
        Order order = (Order) request.getSession().getAttribute("orderData");
        if(status.equalsIgnoreCase("COMPLETED")){
            order.setStatus(2); //Set as completed
            shoppingService.createOrderPaypal(order);
            //Empty cart
            System.out.println("order created: " + order.getSessionId());
            shoppingService.emptyCart(shoppingService.getCart(order.getSessionId()));
            return "redirect:/Index";
        }
        System.out.println("Payment Denied for: " + order.getSessionId());
        return "Frontend/Index/Payment";
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
