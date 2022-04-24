/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bill;
import model.Payment;
import model.PaymentHistory;
import model.Room;

/**
 *
 * @author firem
 */
public class PaymentHistoryDBContext extends DBContext{
    public void insertPaymentHistory(PaymentHistory ph){
        String sql = "INSERT INTO [dbo].[PaymentHistory]\n" +
                    "           ([PaymentId]\n" +
                    "           ,[BillId]\n" +
                    "           ,[FromDate]\n" +
                    "           ,[ToDate])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, ph.getPayment().getId());
            stm.setInt(2, ph.getBill().getId());
            stm.setDate(3, ph.getFromDate());
            stm.setDate(4, ph.getToDate());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PaymentHistoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<PaymentHistory> getListPaymentHistory(){
        String sql = "select Id,PaymentId,BillId,FromDate,ToDate from PaymentHistory";
        ArrayList<PaymentHistory> list = new ArrayList<>();
        PaymentDBContext paymentSql = new PaymentDBContext();
        BillDBContext billSql = new BillDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                PaymentHistory ph = new PaymentHistory();
                ph.setId(rs.getInt("Id"));
                
                Payment payment = paymentSql.getPaymentByIdNull(rs.getInt("PaymentId"));
                ph.setPayment(payment);
                
                Bill bill = billSql.getBillByIdBill(rs.getInt("BillId"));
                ph.setBill(bill);
                
                ph.setFromDate(rs.getDate("FromDate"));
                ph.setToDate(rs.getDate("ToDate"));
                list.add(ph);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentHistoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<PaymentHistory> getListPaymentHistoryByCondition(String fromDate,String toDate,String search){
        String sql = "select h.Id,h.PaymentId,h.BillId,h.FromDate,h.ToDate\n" +
                    "from PaymentHistory as h\n" +
                    "inner join Payment as p on p.Id = h.PaymentId\n" +
                    "inner join [Contract] as c on c.Id = p.ContractId\n" +
                    "inner join Room as r on r.Id = c.RoomId\n" +
                    "where r.[Name] like ? \n";
        if(!fromDate.equals("")){
            sql += "and h.FromDate >= '" + fromDate + "' ";
        }
        if(!toDate.equals("")){
            sql += "and h.FromDate <= '" + toDate + "' ";
        }
        ArrayList<PaymentHistory> list = new ArrayList<>();
        PaymentDBContext paymentSql = new PaymentDBContext();
        BillDBContext billSql = new BillDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                PaymentHistory ph = new PaymentHistory();
                ph.setId(rs.getInt("Id"));
                
                Payment payment = paymentSql.getPaymentByIdNull(rs.getInt("PaymentId"));
                ph.setPayment(payment);
                
                Bill bill = billSql.getBillByIdBill(rs.getInt("BillId"));
                ph.setBill(bill);
                
                ph.setFromDate(rs.getDate("FromDate"));
                ph.setToDate(rs.getDate("ToDate"));
                list.add(ph);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentHistoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<PaymentHistory> getListPaymentHistoryPaging(ArrayList<PaymentHistory> list,int start,int end){
        ArrayList<PaymentHistory> listPaymentHistory = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listPaymentHistory.add(list.get(i));
        }
        return listPaymentHistory;
    }
    
    public ArrayList<PaymentHistory> getListPaymentHistoryByTime(int year,int month){
        String sql = "select h.Id,h.PaymentId,h.BillId,h.FromDate,h.ToDate\n" +
                    "from PaymentHistory as h\n" +
                    "inner join Payment as p on p.Id = h.PaymentId\n" +
                    "inner join [Contract] as c on c.Id = p.ContractId\n" +
                    "inner join Room as r on r.Id = c.RoomId\n" +
                    "where year(h.FromDate) = ? and MONTH(h.FromDate) = ?";
        ArrayList<PaymentHistory> list = new ArrayList<>();
        PaymentDBContext paymentSql = new PaymentDBContext();
        BillDBContext billSql = new BillDBContext();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, year);
            stm.setInt(2, month);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                PaymentHistory ph = new PaymentHistory();
                ph.setId(rs.getInt("Id"));
                
                Payment payment = paymentSql.getPaymentByIdNull(rs.getInt("PaymentId"));
                ph.setPayment(payment);
                
                Bill bill = billSql.getBillByIdBill(rs.getInt("BillId"));
                ph.setBill(bill);
                
                ph.setFromDate(rs.getDate("FromDate"));
                ph.setToDate(rs.getDate("ToDate"));
                list.add(ph);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentHistoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
