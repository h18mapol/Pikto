package piktoproject.pikto.controllers;

import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.models.*;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.UserService;


import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("userData")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @RequestMapping("/Admin")
    public String getAdminPageWithId(Model model, HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cartData");
        System.out.println("User id from Session Cart: " + cart.getUserId()+ " and cartId: " + cart.getCartId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("allProducts",adminService.getAllProducts());
        model.addAttribute("userData",adminService.getUserByEmail(auth.getName()));
        model.addAttribute("allOrders", adminService.getAllOrders());
        System.out.println(auth.getName());
        return "Frontend/Admin/Admin";
    }
    @RequestMapping("/Admin/{userId}")
    public String getAdminPageWithId(Model model, @PathVariable Integer userId){
        model.addAttribute("allProducts",adminService.getAllProducts());
        model.addAttribute("userData",userService.getUserById(userId));
        return "Frontend/Admin/Admin";
    }
    @RequestMapping("/Admin/Products")
    public String getAllProducts(Model model){
        model.addAttribute("allProducts",adminService.getAllProducts());
        return "Frontend/Admin/Products";
    }
    @RequestMapping("/Admin/Users")
    public String getAllUsers(Model model){
        model.addAttribute("allUsers",adminService.getAllUsers());
        return "Frontend/Admin/Users";
    }
    @RequestMapping("/Admin/Reviews")
    public String getAllReviews(Model model){
        model.addAttribute("allReviews",adminService.getAllReviews());
        return "Frontend/Admin/Reviews";
    }
    @RequestMapping("/Admin/Orders")
    public String getAllOrders(Model model){
        model.addAttribute("allOrders", adminService.getAllOrders());
        return "Frontend/Admin/Orders";
    }
    @RequestMapping("/Admin/User/{userId}")
    public String getUserPage(Model model, @PathVariable Integer userId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userData",adminService.getUserByEmail(auth.getName()));
        model.addAttribute("user",adminService.getUser(userId));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(userId));
        model.addAttribute("userReviews",adminService.getAllReviewsById(userId));
        model.addAttribute("userOrders",adminService.getAllOrdersById(userId));
        return "Frontend/Admin/IndividualUser";
    }
    @RequestMapping(path="/Admin/addProduct", method={RequestMethod.POST})
    public String addProduct(@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){

        System.out.println(product.getTitle());
        System.out.println(product.getCategoryId());
        System.out.println(product.getProductUrl());
        System.out.println(product.getPrice());
        System.out.println(product.getContent());
        System.out.println(product.getDiscount());
        System.out.println(product.getPublishedAt());
        System.out.println(product.getType());
        System.out.println(product.getSummary());
        System.out.println(product.getProductId());
        System.out.println(product.getUserId());
        userService.addProduct(product);
        return "redirect:/Admin";

    }
    @RequestMapping(path="/Admin/addUser", method={RequestMethod.POST,RequestMethod.PUT})
    public String addUser(@ModelAttribute ("User")User user,@RequestParam Map<String, String> allRequestParams){
        userService.addUser(user);
        return "redirect:/Admin";
    }
    @RequestMapping(path="/Admin/updateUser", method={RequestMethod.POST})
    public String updateUser(@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.addProduct(product);
        return "redirect:/Admin/Users";
    }
    @RequestMapping(path="/Admin/updateProduct", method={RequestMethod.POST})
    public String updateProduct(Model model,@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.updateProduct(product);
        model.addAttribute("allProducts",adminService.getAllProducts());
        return "Frontend/Admin/Products";
    }
    @RequestMapping(path="/Admin/deleteProduct/{productId}")
    public String deleteProduct(Model model,@PathVariable Integer productId){
        adminService.deleteProduct(productId);
        model.addAttribute("allProducts",adminService.getAllProducts());
        return "Frontend/Admin/Products";
    }

    @RequestMapping(path="/Admin/updateOrder", method={RequestMethod.POST})
    public String updateOrder(Model model, @ModelAttribute ("order") Order order, @RequestParam Map<String, String> allRequestParams){
        adminService.updateOrder(order);
        order.getFirstName();
        order.getLastName();
        model.addAttribute("allOrders", adminService.getAllOrders());
        return "Frontend/Admin/Orders";
    }
    @RequestMapping(path="/Admin/deleteOrder/{orderId}")
    public String deleteOrder(Model model,@PathVariable Integer orderId){
        adminService.deleteOrder(orderId);
        model.addAttribute("allOrders", adminService.getAllOrders());
        return "Frontend/Admin/Orders";
    }

    @RequestMapping(path="/Admin/updateReview", method={RequestMethod.POST})
    public String updateReview(Model model, @ModelAttribute ("product_review") Product_review product_review, @RequestParam Map<String, String> allRequestParams){
        adminService.updateReview(product_review);
        model.addAttribute("allReviews",adminService.getAllReviews());
        return "Frontend/Admin/Reviews";
    }
    @RequestMapping(path="/Admin/deleteReview/{reviewId}")
    public String deleteReview(Model model,@PathVariable Integer reviewId){
        adminService.deleteReview(reviewId);
        model.addAttribute("allReviews",adminService.getAllReviews());
        return "Frontend/Admin/Reviews";
    }



    @RequestMapping(path="/Admin/User/updateProduct", method={RequestMethod.POST})
    public String updateUserProduct(Model model,@ModelAttribute ("product")Product product,@RequestParam Map<String, String> allRequestParams){
        userService.updateProduct(product);
        model.addAttribute("userData",adminService.getUser(product.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(product.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(product.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(product.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }
    @RequestMapping(path="/Admin/User/deleteProduct/{productId}")
    public String deleteUserProduct(Model model,@PathVariable Integer productId){
        Product product = adminService.getProduct(productId);
        adminService.deleteProduct(productId);
        model.addAttribute("userData",adminService.getUser(product.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(product.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(product.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(product.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }

    @RequestMapping(path="/Admin/User/updateOrder", method={RequestMethod.POST})
    public String updateUserOrder(Model model, @ModelAttribute ("order") Order order, @RequestParam Map<String, String> allRequestParams){
        adminService.updateOrder(order);
        model.addAttribute("userData",adminService.getUser(order.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(order.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(order.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(order.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }
    @RequestMapping(path="/Admin/User/deleteOrder/{orderId}")
    public String deleteUserOrder(Model model,@PathVariable Integer orderId){
        Order order = adminService.getOrder(orderId);
        adminService.deleteOrder(orderId);
        model.addAttribute("userData",adminService.getUser(order.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(order.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(order.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(order.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }

    @RequestMapping(path="/Admin/User/updateReview", method={RequestMethod.POST})
    public String updateUserReview(Model model, @ModelAttribute ("product_review") Product_review product_review, @RequestParam Map<String, String> allRequestParams){
        adminService.updateReview(product_review);
        model.addAttribute("userData",adminService.getUser(product_review.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(product_review.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(product_review.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(product_review.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }
    @RequestMapping(path="/Admin/User/deleteReview/{reviewId}")
    public String deleteUserReview(Model model,@PathVariable Integer reviewId){
        Product_review product_review = adminService.getReviewById(reviewId);
        adminService.deleteReview(reviewId);
        model.addAttribute("userData",adminService.getUser(product_review.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(product_review.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(product_review.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(product_review.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }

    @RequestMapping(path="/Admin/User/updateUser", method={RequestMethod.POST})
    public String updateUser(Model model, @ModelAttribute ("user") User user, @RequestParam Map<String, String> allRequestParams){
        adminService.updateUser(user);
        model.addAttribute("userData",adminService.getUser(user.getUserId()));
        model.addAttribute("userProducts",adminService.getAllProductsbyId(user.getUserId()));
        model.addAttribute("userReviews",adminService.getAllReviewsById(user.getUserId()));
        model.addAttribute("userOrders",adminService.getAllOrdersById(user.getUserId()));
        return "Frontend/Admin/IndividualUser";
    }

    @RequestMapping("/Index/klarna")
    public String getUserPage(Model model){
        return "klarna";
    }
    //Klarna API Username (UID): PK35775_806b73fafab9
    //Klarna API Password (UID): jxp23fYNvMCGoksK
}

