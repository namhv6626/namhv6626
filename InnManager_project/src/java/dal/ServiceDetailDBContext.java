/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.ServiceDetail;
import model.ServiceType;

/**
 *
 * @author firem
 */
public class ServiceDetailDBContext extends DBContext{
    public ArrayList<ServiceDetail> getListServiceDetailByBillId(int id){
        String sql = "select b.Id,b.Quanlity,b.Price as PriceServiceBill,\n" +
                    "b.ServiceTypeId,s.[Name],s.Price,s.Unit,\n" +
                    "b.BillId,l.PriceTotal\n" +
                    "from ServiceBill as b\n" +
                    "inner join ServiceType as s on b.ServiceTypeId = s.Id\n" +
                    "inner join Bill as l on b.BillId = l.Id\n" +
                    "where b.BillId = ? ";
        ArrayList<ServiceDetail> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ServiceDetail sd = new ServiceDetail();
                sd.setId(rs.getInt("Id"));
                sd.setPrice(rs.getDouble("PriceServiceBill"));
                
                Bill bill = new Bill();
                bill.setId(rs.getInt("BillId"));
                bill.setPrice(rs.getDouble("PriceTotal"));
                sd.setBill(bill);
                
                sd.setQuantity(rs.getInt("Quanlity"));
                
                ServiceType st = new ServiceType();
                st.setId(rs.getInt("ServiceTypeId"));
                st.setName(rs.getString("Name"));
                st.setPrice(rs.getDouble("Price"));
                st.setUnit(rs.getString("Unit"));
                sd.setService(st);
                
                list.add(sd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void insertServiceBill(int idBill,int idService,double price,int quantity){
        String sql = "INSERT INTO [dbo].[ServiceBill]\n" +
                    "           ([BillId]\n" +
                    "           ,[ServiceTypeId]\n" +
                    "           ,[Price]\n" +
                    "           ,[Quanlity])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, idBill);
            stm.setInt(2, idService);
            stm.setDouble(3, price);
            stm.setInt(4, quantity);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
