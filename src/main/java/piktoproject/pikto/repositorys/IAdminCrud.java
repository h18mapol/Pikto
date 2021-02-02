package piktoproject.pikto.repositorys;

import java.util.List;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.product;

@Repository
public interface IAdminCrud {
       public void getAllProducts();
       public product getProduct(int productID);
       public product updateProduct(int productID);
       public void deleteProduct(int productID);
}
