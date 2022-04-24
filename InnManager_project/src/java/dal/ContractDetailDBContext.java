/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author firem
 */
public class ContractDetailDBContext extends DBContext{
    public void insertContractDetail(int id,double price){
        String sql = "INSERT INTO [dbo].[ContractDetail]\n" +
                "           ([ContractId]\n" +
                "           ,[PriceRoomType])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDouble(2, price);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ContractDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
