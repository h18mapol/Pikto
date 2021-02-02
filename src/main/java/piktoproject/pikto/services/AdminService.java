package piktoproject.pikto.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import piktoproject.pikto.models.product;
import piktoproject.pikto.repositorys.IAdminCrud;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends SpringBootServletInitializer {
    @Autowired
    private IAdminCrud crud;

    public void getAllProducts(){
        crud.getAllProducts();
    }
    public product getProduct(int productID){
        return crud.getProduct(productID);
    }
    public product updateProduct(int productID){
        return updateProduct(productID);
    }

    public void deleteProduct(int productID){
        deleteProduct(productID);
    }
}
