package piktoproject.pikto.repositorys;

import java.util.List;
import piktoproject.pikto.models.product;

public interface IAdminCrud {
       public List<product>getAllProducts();
       public product getProduct(int productID);
       
       public product updateProduct(int productID);
       public void deleteProduct(int productID);


}
