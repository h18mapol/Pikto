package piktoproject.pikto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import piktoproject.pikto.models.Order;
import piktoproject.pikto.services.AdminService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Controller
public class DataController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/Data/Orders", method = RequestMethod.GET)
    public ResponseEntity<?> index(Model model) {
        List<Order> result = adminService.getAllOrders();
        return ResponseEntity.ok(result);
    }
}
