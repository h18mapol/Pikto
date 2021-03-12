package piktoproject.pikto.repositorys;

import org.springframework.stereotype.Repository;
import piktoproject.pikto.models.DataDTO;
import piktoproject.pikto.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class DataCrud implements IDataCrud{
    private Connection con;

    @Override
    public List<DataDTO> getRevenueData() {
        List <DataDTO> dataDTOS=new ArrayList<>();
        try{
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/piktodb?serverTimezone=UTC","root","");
            Statement statement =con.createStatement();
            statement=con.createStatement();
            String sqlSelectRevenue="SELECT (userId), SUM(grandTotal) \n" +
                    "FROM piktodb.order\n" +
                    "GROUP BY (userId), (userId)" +
                    "order by SUM(grandTotal) desc";
            ResultSet resultset=statement.executeQuery(sqlSelectRevenue);
            while (resultset.next()){
                DataDTO dataDTO=new DataDTO();
                dataDTO.setUserId(resultset.getInt(1));
                dataDTO.setGrandTotal(resultset.getDouble(2));
                dataDTOS.add(dataDTO);
            }
            resultset.close();
            statement.close();
            con.close();
            System.out.println("TEST ---->> data " + dataDTOS.get(0).getUserId() + dataDTOS.get(0).getGrandTotal());
            return dataDTOS;
        } catch (SQLException ex) {
            Logger.getLogger(DataCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } //Klar

}
