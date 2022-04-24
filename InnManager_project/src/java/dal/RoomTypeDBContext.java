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
import model.Customer;
import model.Room;
import model.RoomType;

/**
 *
 * @author firem
 */
public class RoomTypeDBContext extends DBContext{
    public ArrayList<RoomType> getListRoomType(){
        String sql = "select Id,[Name],Quantity,Area,Price from RoomType";
        ArrayList<RoomType> listRoomType = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("Id"));
                rt.setName(rs.getString("Name"));
                rt.setQuantity(rs.getInt("Quantity"));
                rt.setArea(rs.getDouble("Area"));
                rt.setPrice(rs.getDouble("Price"));
                listRoomType.add(rt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoomType;
    }
    
    public ArrayList<RoomType> getListBySearch(String search){
        String sql = "select Id,[Name],Quantity,Area,Price from RoomType\n" +
                    "where [Name] like ?";
        ArrayList<RoomType> listRoomType = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+search+"%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("Id"));
                rt.setName(rs.getString("Name"));
                rt.setQuantity(rs.getInt("Quantity"));
                rt.setArea(rs.getDouble("Area"));
                rt.setPrice(rs.getDouble("Price"));
                listRoomType.add(rt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoomType;
    }
    
    public boolean getRoomTypeByName(String name){
        String sql = "select Id,[Name],Quantity,Area,Price from RoomType\n" +
                    "where [Name] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public RoomType getRoomTypeById(int id){
        String sql = "select Id,[Name],Quantity,Area,Price from RoomType\n" +
                    "where Id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("Id"));
                rt.setName(rs.getString("Name"));
                rt.setQuantity(rs.getInt("Quantity"));
                rt.setArea(rs.getDouble("Area"));
                rt.setPrice(rs.getDouble("Price"));
                return rt;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insertRoomType(RoomType rt){
        String sql = "insert into RoomType\n" +
                    "		([Name],\n" +
                    "		Quantity,\n" +
                    "		Area,\n" +
                    "		Price)\n" +
                    "		values\n" +
                    "		(?,\n" +
                    "		?,\n" +
                    "		?,\n" +
                    "		?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, rt.getName());
            stm.setInt(2, rt.getQuantity());
            stm.setDouble(3, rt.getArea());
            stm.setDouble(4, rt.getPrice());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(stm != null)
            {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection !=null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void updateRoomType(RoomType rt){
        String sql = "update RoomType set [Name] = ?,\n" +
                "       Quantity = ? ,\n" +
                "       Area = ? ,\n" +
                "       Price = ? \n" +
                "where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, rt.getName());
            stm.setInt(2, rt.getQuantity());
            stm.setDouble(3, rt.getArea());
            stm.setDouble(4, rt.getPrice());
            stm.setInt(5, rt.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkQuantityCustomer(int id,int quantity){
        RoomDBContext roomSql = new RoomDBContext();
        ArrayList<Room> listRoom = roomSql.getListRoomByRoomTypeId(id);
        CustomerDBContext customerSql = new CustomerDBContext();
        for (Room room : listRoom) {
            ArrayList<Customer> listCustomer = customerSql.getListCustomerByRoomId(room.getId());
            if(listCustomer.size() > quantity){
                return false;
            }
        }
        return true;
    }
    
    public void deleteRoomType(int id){
        String sql = "delete RoomType where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
