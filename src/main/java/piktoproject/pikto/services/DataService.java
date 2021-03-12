package piktoproject.pikto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import piktoproject.pikto.models.*;
import piktoproject.pikto.repositorys.IAdminCrud;
import piktoproject.pikto.repositorys.IDataCrud;

@Service
public class DataService {

    @Autowired
    private IDataCrud dataCrud;

    public List<DataDTO> getRevenueData() {
        return dataCrud.getRevenueData();
    }
}