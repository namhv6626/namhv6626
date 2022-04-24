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
import model.Conduct;
import model.ConductDetail;
import model.ConductType;

/**
 *
 * @author firem
 */
public class ConductDBContext extends DBContext{
    public ArrayList<Conduct> getListConduct(){
        String sql = "select c.Id,c.[Name],c.Price,c.TypeId,t.[Name] as ConductTypeName\n" +
                    "from Conduct as c\n" +
                    "inner join ConductType as t on c.TypeId = t.Id";
        ArrayList<Conduct> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Conduct cd = new Conduct();
                cd.setId(rs.getInt("Id"));
                cd.setName(rs.getString("Name"));
                cd.setPrice(rs.getDouble("Price"));
                
                ConductType ct = new ConductType();
                ct.setId(rs.getInt("TypeId"));
                ct.setName(rs.getString("ConductTypeName"));
                
                cd.setConductType(ct);
                list.add(cd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<ConductDetail> getListConducttDetail(ArrayList<Conduct> list){
        boolean[] listCheck = new boolean[list.size()];
        ArrayList<ConductDetail> listConductDetail = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if(!listCheck[i]){
                int count = 0;
                for (int j = i ; j < list.size(); j++) {
                    if (list.get(i).getId() == list.get(j).getId()) {
                        listCheck[j] = true;
                        count++;
                    }
                }
                ConductDetail conductDetail = new ConductDetail();
                Conduct cd = new Conduct();
                cd.setId(list.get(i).getId());
                cd.setName(list.get(i).getName());
                cd.setPrice(list.get(i).getPrice());
                cd.setConductType(list.get(i).getConductType());
                
                conductDetail.setConduct(cd);
                conductDetail.setQuantity(count);
                listConductDetail.add(conductDetail);
            }
        }
        return listConductDetail;
    }
    
    public void insertConductService(int conductId,int contractId){
        String sql = "INSERT INTO [dbo].[ConductService]\n" +
                    "           ([ConductId]\n" +
                    "           ,[ContractId]\n" +
                    "           ,[Status]\n" +
                    "           ,[Reason])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
             stm.setInt(1, conductId);
             stm.setInt(2, contractId);
             stm.setBoolean(3, true);
             stm.setNull(4, Types.LONGVARCHAR);
             stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePriceConduct(int id,double price){
        String sql = "update Conduct set Price = ? \n" +
                    "where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDouble(1, price);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateConductService(int idContract,int idConduct,String reason){
        String sql = "update ConductService set [Status] = 0, Reason = ?\n" +
                    "where ContractId = ? and ConductId = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, reason);
            stm.setInt(2, idContract);
            stm.setInt(3, idConduct);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
