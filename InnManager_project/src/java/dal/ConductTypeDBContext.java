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
import model.ConductType;

/**
 *
 * @author firem
 */
public class ConductTypeDBContext extends DBContext{
    public ArrayList<ConductType> getListConductType(){
        String sql = "select Id,[Name] from ConductType";
        ArrayList<ConductType> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ConductType ct = new ConductType();
                ct.setId(rs.getInt("Id"));
                ct.setName(rs.getString("Name"));
                list.add(ct);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConductTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
