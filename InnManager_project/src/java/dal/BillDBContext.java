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

/**
 *
 * @author firem
 */
public class BillDBContext extends DBContext{
    public void insertBill(){
        String sql = "INSERT INTO [dbo].[Bill]\n" +
                    "           ([PriceTotal])\n" +
                    "     VALUES\n" +
                    "           (?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, 0);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int getBillInLast(){
        String sql = "select top(1) Id from Bill\n" +
                    "order by Id desc";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getInt("Id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public void updatePrice(int id){
        String sql = "update Bill set PriceTotal = (\n" +
"							select sum(s.Quanlity * t.Price)\n" +
"							from ServiceType as t \n" +
"							inner join ServiceBill as s on t.Id = s.ServiceTypeId\n" +
"							inner join Bill as b on b.Id = s.BillId\n" +
"							where b.Id = ? \n" +
"							group by b.Id\n" +
"						)\n" +
"                   where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Bill getBillByIdBill(int id){
        String sql = "select Id,PriceTotal from Bill\n" +
                    "where Id = ? ";
        ServiceDetailDBContext serviceTypeSql = new ServiceDetailDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("Id"));
                bill.setPrice(rs.getDouble("PriceTotal"));
                ArrayList<ServiceDetail> listServiceDetail = serviceTypeSql.getListServiceDetailByBillId(rs.getInt("Id"));
                bill.setListService(listServiceDetail);
                return bill;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Bill> getListBill(){
        String sql = "select Id,PriceTotal from Bill";
        ServiceDetailDBContext serviceTypeSql = new ServiceDetailDBContext();
        ArrayList<Bill> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Bill bill = new Bill();
                bill.setId(rs.getInt("Id"));
                bill.setPrice(rs.getDouble("PriceTotal"));
                ArrayList<ServiceDetail> listServiceDetail = serviceTypeSql.getListServiceDetailByBillId(rs.getInt("Id"));
                bill.setListService(listServiceDetail);
                
                list.add(bill);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
