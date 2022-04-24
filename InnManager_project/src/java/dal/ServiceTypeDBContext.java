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
import model.ServiceType;

/**
 *
 * @author firem
 */
public class ServiceTypeDBContext extends DBContext{
    public ArrayList<ServiceType> getListServiceType(){
        String sql = "select Id,[Name],Unit,Price from ServiceType";
        ArrayList<ServiceType> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ServiceType st = new ServiceType();
                st.setId(rs.getInt("Id"));
                st.setName(rs.getString("Name"));
                st.setUnit(rs.getString("Unit"));
                st.setPrice(rs.getDouble("Price"));
                list.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void updatePriceService(int id,double price){
        String sql = "update ServiceType set Price = ?\n" +
                    "where Id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, price);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
