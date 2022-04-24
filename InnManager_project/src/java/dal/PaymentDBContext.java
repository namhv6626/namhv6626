/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.Contract;
import model.Payment;
import model.ServiceDetail;

/**
 *
 * @author firem
 */
public class PaymentDBContext extends DBContext{
    
    public ArrayList<Payment> getListPaymentToCreate(){
        String sql = "select Id,ContractId,CurrentBillId,FromDate,ToDate,[Status] from Payment\n" +
                    "where CurrentBillId is Null and [Status] = 0 and ToDate <= GETDATE() ";
        ContractDBContext contractSql = new ContractDBContext();
        ArrayList<Payment> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Payment pm = new Payment();
                pm.setId(rs.getInt("Id"));
                Contract contract = contractSql.getContractById(rs.getInt("ContractId"));
                pm.setContract(contract);
                pm.setFromDate(rs.getDate("FromDate"));
                pm.setToDate(rs.getDate("ToDate"));
                pm.setStatus(rs.getBoolean("Status"));
                
                list.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Payment> getListPaymentToPay(){
        String sql = "select p.Id,p.ContractId,p.FromDate,p.ToDate,p.[Status],\n" +
                    "p.CurrentBillId,b.PriceTotal\n" +
                    "from Payment as p\n" +
                    "inner join Bill as b on p.CurrentBillId = b.Id\n" +
                    "where CurrentBillId is not Null and [Status] = 0 ";
        ContractDBContext contractSql = new ContractDBContext();
        ServiceDetailDBContext serviceTypeSql = new ServiceDetailDBContext();
        ArrayList<Payment> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Payment pm = new Payment();
                pm.setId(rs.getInt("Id"));
                Contract contract = contractSql.getContractById(rs.getInt("ContractId"));
                pm.setContract(contract);
                pm.setFromDate(rs.getDate("FromDate"));
                pm.setToDate(rs.getDate("ToDate"));
                pm.setStatus(rs.getBoolean("Status"));
                
                Bill bill = new Bill();
                bill.setId(rs.getInt("CurrentBillId"));
                bill.setPrice(rs.getDouble("PriceTotal"));
                ArrayList<ServiceDetail> listServiceDetail = serviceTypeSql.getListServiceDetailByBillId(rs.getInt("CurrentBillId"));
                bill.setListService(listServiceDetail);
                pm.setBill(bill);
                
                list.add(pm);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void insertPayment(Payment pm){
        String sql = "INSERT INTO [dbo].[Payment]\n" +
                    "           ([ContractId]\n" +
                    "           ,[CurrentBillId]\n" +
                    "           ,[FromDate]\n" +
                    "           ,[ToDate]\n" +
                    "           ,[Status])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pm.getContract().getId());
            stm.setNull(2, Types.INTEGER);
            stm.setDate(3, pm.getFromDate());
            stm.setDate(4, pm.getToDate());
            stm.setBoolean(5, false);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateCurrentBillId(int idBill ,int idPayment){
        String sql = "update Payment set CurrentBillId = ?\n" +
                    "where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, idBill);
            stm.setInt(2, idPayment);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Payment getPaymentByIdNotNull(int id){
        String sql = "select p.Id,p.ContractId,p.FromDate,p.ToDate,p.[Status],\n" +
                    "p.CurrentBillId,b.PriceTotal\n" +
                    "from Payment as p\n" +
                    "inner join Bill as b on p.CurrentBillId = b.Id\n" +
                    "where p.Id = ? ";
        ContractDBContext contractSql = new ContractDBContext();
        ServiceDetailDBContext serviceTypeSql = new ServiceDetailDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Payment pm = new Payment();
                pm.setId(rs.getInt("Id"));
                Contract contract = contractSql.getContractById(rs.getInt("ContractId"));
                pm.setContract(contract);
                pm.setFromDate(rs.getDate("FromDate"));
                pm.setToDate(rs.getDate("ToDate"));
                pm.setStatus(rs.getBoolean("Status"));
                
                Bill bill = new Bill();
                bill.setId(rs.getInt("CurrentBillId"));
                bill.setPrice(rs.getDouble("PriceTotal"));
                ArrayList<ServiceDetail> listServiceDetail = serviceTypeSql.getListServiceDetailByBillId(rs.getInt("CurrentBillId"));
                bill.setListService(listServiceDetail);
                pm.setBill(bill);
                
                return pm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Payment getPaymentByIdNull(int id){
        String sql = "select p.Id,p.ContractId,p.CurrentBillId,p.FromDate,p.ToDate,p.[Status]\n" +
                    "from Payment as p\n" +
                    "where p.Id = ? ";
        ContractDBContext contractSql = new ContractDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Payment pm = new Payment();
                pm.setId(rs.getInt("Id"));
                Contract contract = contractSql.getContractById(rs.getInt("ContractId"));
                pm.setContract(contract);
                pm.setFromDate(rs.getDate("FromDate"));
                pm.setToDate(rs.getDate("ToDate"));
                pm.setStatus(rs.getBoolean("Status"));
                
                return pm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateNewCurrentPayment(Payment pm){
        String sql = "UPDATE [dbo].[Payment]\n" +
                    "   SET [CurrentBillId] = ?\n" +
                    "      ,[FromDate] = ?\n" +
                    "      ,[ToDate] = ?\n" +
                    " WHERE Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setNull(1, Types.INTEGER);
            stm.setDate(2, pm.getFromDate());
            stm.setDate(3,pm.getToDate());
            stm.setInt(4, pm.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateStatusPayment(int id){
        String sql = "update Payment set [Status] = 1\n" +
                    "where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Payment getPaymentByContractId(int id){
        String sql = "select p.Id,p.ContractId,p.CurrentBillId,p.FromDate,p.ToDate,p.[Status]\n" +
                    "from Payment as p\n" +
                    "inner join [Contract] as c on p.ContractId = c.Id\n" +
                    "where c.Id = ? ";
        ContractDBContext contractSql = new ContractDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Payment pm = new Payment();
                pm.setId(rs.getInt("Id"));
                Contract contract = contractSql.getContractById(rs.getInt("ContractId"));
                pm.getBill().setId(rs.getInt("CurrentBillId"));
                pm.setContract(contract);
                pm.setFromDate(rs.getDate("FromDate"));
                pm.setToDate(rs.getDate("ToDate"));
                pm.setStatus(rs.getBoolean("Status"));
                
                return pm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
