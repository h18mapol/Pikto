package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import piktoproject.pikto.models.*;
import piktoproject.pikto.repositorys.DataCrud;
import piktoproject.pikto.services.AdminService;
import piktoproject.pikto.services.DataService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/Data/Orders", method = RequestMethod.GET)
    public ResponseEntity<?> index(Model model) {
        List<Order> result = adminService.getAllOrders();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/Data/Orders/Revenue", method = RequestMethod.GET)
    public ResponseEntity<?> getRevenueByUser(Model model) {
        List<DataDTO> result = dataService.getRevenueData();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/Data/Users/Data", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(Model model) {
        List<User> result = adminService.getAllUsers();
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/Data/Products/Data", method = RequestMethod.GET)
    public ResponseEntity<?> getProducts(Model model) {
        List<Product> result = adminService.getAllProducts();
        System.out.println(result.size());
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/Data/Reviews/Data", method = RequestMethod.GET)
    public ResponseEntity<?> getReviews(Model model) {
        List<Product_review> result = adminService.getAllReviews();
        System.out.println(result.size());
        return ResponseEntity.ok(result);
    }
}
