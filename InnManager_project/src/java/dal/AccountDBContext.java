/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author firem
 */
public class AccountDBContext extends DBContext{
    public Account getAccount(String username,String password){
        String sql = "select Id,Username,[Password] from Account\n" +
                    "where Username = ? and [Password] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account ac = new Account();
                ac.setId(rs.getInt("Id"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                return ac;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insertAccount(String username,String password){
        String sql = "INSERT INTO [dbo].[Account]\n" +
                    "           ([Username]\n" +
                    "           ,[Password])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getAccountInLast(){
        String sql = "select top(1) Id from Account\n" +
                    "order by Id desc";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("Id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
