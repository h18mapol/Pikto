package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import piktoproject.pikto.login.facebook.Facebook;
import piktoproject.pikto.models.User;
import piktoproject.pikto.repositorys.UserCrud;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.GoogleInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;
@SessionAttributes("userId")
@Controller
public class LoginController {

    @Autowired
    AdminService adminService;

    private Facebook facebook;
    @Autowired
    GoogleInfoService googleInfoService;

    @Autowired
    OAuth2AuthorizedClientService authclientService;

    @Autowired
    public LoginController(Facebook facebook) {
        this.facebook = facebook;
    }

    @GetMapping("/login")
    public String loadLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/")
    public String facebook(Model model) {
        return "home";
    }



    @RequestMapping("/formLogin")
    public String getformLoginInfo(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = adminService.getUserByEmail(auth.getName());
        model.addAttribute("userData", user);
        model.addAttribute("userId", user.getUserId());
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            System.out.println("Role Admin -->");
            return "redirect:/Admin";
        } else {
            System.out.println("Role Vanliga User -->");
            model.addAttribute("userProducts",adminService.getAllProductsbyId(user.getUserId()));
            model.addAttribute("userReviews",adminService.getAllReviewsById(user.getUserId()));
            model.addAttribute("userOrders",adminService.getAllOrdersById(user.getUserId()));
            return "redirect:/User";
        }
    }

    @RequestMapping("/oauth2LoginSuccess")
    public String getOauth2LoginInfo(Model model) {
        System.out.println("Social Login!");
        OAuth2AuthorizedClientService clientService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
        switch (clientRegistrationId){
            case "facebook":
                System.out.println(oauthToken.getPrincipal().getAttributes().toString());
                String facebookEmail = (String) oauthToken.getPrincipal().getAttributes().get("email");
                String facebookname = (String) oauthToken.getPrincipal().getAttributes().get("name");
                User user = adminService.getUserByEmail(facebookEmail);
                if (user.getEmail() == null){
                    int idx = facebookname.lastIndexOf(' ');
                    if (idx == -1)
                        throw new IllegalArgumentException("Only a single name: " + facebookname);
                    String firstName = facebookname.substring(0, idx);
                    String lastName  = facebookname.substring(idx + 1);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(facebookEmail);
                    user.setPassword("password");
                    user.setMobileNr("");
                    user.setPictureUrl("");
                    adminService.addUser(user);
                    User facebookUser = adminService.getUserByEmail(facebookEmail);
                    model.addAttribute("userDate", facebookUser);
                    model.addAttribute("userProducts",adminService.getAllProductsbyId(facebookUser.getUserId()));
                    model.addAttribute("userReviews",adminService.getAllReviewsById(facebookUser.getUserId()));
                    model.addAttribute("userOrders",adminService.getAllOrdersById(facebookUser.getUserId()));
                } else {
                    System.out.println("Already a user");
                    model.addAttribute("userData", user);
                    model.addAttribute("userProducts",adminService.getAllProductsbyId(user.getUserId()));
                    model.addAttribute("userReviews",adminService.getAllReviewsById(user.getUserId()));
                    model.addAttribute("userOrders",adminService.getAllOrdersById(user.getUserId()));
                }
                if (user.getAdmin() == 1){
                    System.out.println("Role Admin -->");
                    return "Frontend/Admin/Users";
                } else {
                    System.out.println("Role Vanliga User -->");
                    return "Frontend/User/userPage";
                }
            case "google":
                String googleEmail = (String) oauthToken.getPrincipal().getAttributes().get("email");
                User googleUser = adminService.getUserByEmail(googleEmail);
                if (googleUser.getEmail() == null){
                    googleUser.setPassword("password");
                    googleUser.setEmail(googleEmail);
                    googleUser.setFirstName((String) oauthToken.getPrincipal().getAttributes().get("given_name"));
                    googleUser.setLastName((String) oauthToken.getPrincipal().getAttributes().get("family_name"));
                    googleUser.setPictureUrl((String) oauthToken.getPrincipal().getAttributes().get("picture"));
                    googleUser.setMobileNr("");
                    adminService.addUser(googleUser);
                    googleUser = adminService.getUserByEmail(googleEmail);
                } else {
                    System.out.println("Already a user");
                }
                model.addAttribute("userData", googleUser);
                model.addAttribute("userProducts",adminService.getAllProductsbyId(googleUser.getUserId()));
                model.addAttribute("userReviews",adminService.getAllReviewsById(googleUser.getUserId()));
                model.addAttribute("userOrders",adminService.getAllOrdersById(googleUser.getUserId()));
                if (googleUser.getAdmin() == 1){
                    System.out.println("Role Admin -->");
                    return "Frontend/Admin/Users";
                } else {
                    System.out.println("Role Vanliga User -->");
                    return "Frontend/User/userPage";
                }
            case "github":
                System.out.println(oauthToken.getPrincipal().getAttributes().toString());
                String githubEmail = (String) oauthToken.getPrincipal().getAttributes().get("login")+"@github.com";
                String githubname = (String) oauthToken.getPrincipal().getAttributes().get("name");
                System.out.println(githubEmail + "email : name " + githubname);
                User githubUser = adminService.getUserByEmail(githubEmail);
                if (githubUser.getEmail() == null){
                    int idx = githubname.lastIndexOf(' ');
                    if (idx == -1)
                        throw new IllegalArgumentException("Only a single name: " + githubname);
                    String firstName = githubname.substring(0, idx);
                    String lastName  = githubname.substring(idx + 1);
                    githubUser.setFirstName(firstName);
                    githubUser.setLastName(lastName);
                    githubUser.setMobileNr("");
                    githubUser.setPassword("password");
                    githubUser.setEmail(githubEmail);
                    githubUser.setPictureUrl((String) oauthToken.getPrincipal().getAttributes().get("avatar_url"));
                    adminService.addUser(githubUser);
                    githubUser = adminService.getUserByEmail(githubEmail);
                } else {
                    System.out.println("Already a user");
                }
                model.addAttribute("userData", githubUser);
                model.addAttribute("userProducts",adminService.getAllProductsbyId(githubUser.getUserId()));
                model.addAttribute("userReviews",adminService.getAllReviewsById(githubUser.getUserId()));
                model.addAttribute("userOrders",adminService.getAllOrdersById(githubUser.getUserId()));
                if (githubUser.getAdmin() == 1){
                    System.out.println("Role Admin -->");
                    return "Frontend/Admin/Users";
                } else {
                    System.out.println("Role Vanliga User -->");
                    return "Frontend/User/userPage";
                }
            default:
                return "login";
        }
    }


    @RequestMapping("/formLoginSuccess")
    public String getOauth2LoginFormInfo(Model model){
        OAuth2AuthorizedClientService clientService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.toString());
        return "home";
    }
}