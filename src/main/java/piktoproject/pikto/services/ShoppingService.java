package piktoproject.pikto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.Product;
import piktoproject.pikto.repositorys.IAdminCrud;
import java.util.List;

@Service
public class ShoppingService {

    @Autowired
    private IAdminCrud adminCrud;

    public List<Product> getAllProducts() {
        return adminCrud.getAllProducts();
    }
}
