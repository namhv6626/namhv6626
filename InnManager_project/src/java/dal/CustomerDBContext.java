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
import model.Account;
import model.Customer;
import model.Room;
import model.RoomType;

/**
 *
 * @author firem
 */
public class CustomerDBContext extends DBContext{
    public ArrayList<Customer> getListCustomer(){
        String sql = "select c.Id,c.[Name],c.DateOfBirth,c.Gender,c.PhoneNumber,c.IdentityCard,c.[Address],c.Email,c.HireDate,c.[Status],\n" +
                    "c.RoomId,r.[Name] as NameRoom,r.[Floor],r.[Status] as statusRoom,r.TypeId,\n" +
                    "c.AccountId,a.Username,a.[Password]\n" +
                    "from Customer as c \n" +
                    "inner join Room as r on c.RoomId = r.Id\n" +
                    "inner join Account as a on c.AccountId = a.Id";
        ArrayList<Customer> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Customer cs = new Customer();
                cs.setId(rs.getInt("Id"));
                cs.setName(rs.getString("Name"));
                cs.setDob(rs.getDate("DateOfBirth"));
                cs.setGender(rs.getBoolean("Gender"));
                cs.setPhone(rs.getString("PhoneNumber"));
                cs.setIdentity(rs.getString("IdentityCard"));
                cs.setAddress(rs.getString("Address"));
                cs.setEmail(rs.getString("Email"));
                cs.setHireDate(rs.getDate("HireDate"));
                cs.setStatus(rs.getBoolean("Status"));
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setName(rs.getString("NameRoom"));
                room.setFloor(rs.getInt("Floor"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                room.setRoomType(rt);
                cs.setRoom(room);
                Account ac = new Account();
                ac.setId(rs.getInt("AccountId"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                cs.setAccount(ac);
                list.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Customer> getListCustomerByCondition(int status,String search){
        String sql = "select c.Id,c.[Name],c.DateOfBirth,c.Gender,c.PhoneNumber,c.IdentityCard,c.[Address],c.Email,c.HireDate,c.[Status],\n" +
                    "c.RoomId,r.[Name] as NameRoom,r.[Floor],r.[Status] as statusRoom,r.TypeId,\n" +
                    "c.AccountId,a.Username,a.[Password]\n" +
                    "from Customer as c \n" +
                    "inner join Room as r on c.RoomId = r.Id\n" +
                    "inner join Account as a on c.AccountId = a.Id\n" +
                    "where c.[Name] like ? \n";
        if(status > -1){
            sql += "and c.[Status] = " + status + " ";
        }
        ArrayList<Customer> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Customer cs = new Customer();
                cs.setId(rs.getInt("Id"));
                cs.setName(rs.getString("Name"));
                cs.setDob(rs.getDate("DateOfBirth"));
                cs.setGender(rs.getBoolean("Gender"));
                cs.setPhone(rs.getString("PhoneNumber"));
                cs.setIdentity(rs.getString("IdentityCard"));
                cs.setAddress(rs.getString("Address"));
                cs.setEmail(rs.getString("Email"));
                cs.setHireDate(rs.getDate("HireDate"));
                cs.setStatus(rs.getBoolean("Status"));
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setName(rs.getString("NameRoom"));
                room.setFloor(rs.getInt("Floor"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                room.setRoomType(rt);
                cs.setRoom(room);
                Account ac = new Account();
                ac.setId(rs.getInt("AccountId"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                cs.setAccount(ac);
                list.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<Customer> getListCustomerByRoomId(int id){
        String sql = "select c.Id,c.[Name],c.DateOfBirth,c.Gender,c.PhoneNumber,c.IdentityCard,c.[Address],c.Email,c.HireDate,c.[Status],\n" +
                        "c.RoomId,r.[Name] as NameRoom,r.[Floor],r.[Status] as statusRoom,r.TypeId,\n" +
                        "c.AccountId,a.Username,a.[Password]\n" +
                        "from Customer as c\n" +
                        "inner join Room as r on c.RoomId = r.Id\n" +
                        "inner join Account as a on c.AccountId = a.Id\n" +
                        "where r.Id = ? and c.[Status] = 1 ";
        ArrayList<Customer> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Customer cs = new Customer();
                cs.setId(rs.getInt("Id"));
                cs.setName(rs.getString("Name"));
                cs.setDob(rs.getDate("DateOfBirth"));
                cs.setGender(rs.getBoolean("Gender"));
                cs.setPhone(rs.getString("PhoneNumber"));
                cs.setIdentity(rs.getString("IdentityCard"));
                cs.setAddress(rs.getString("Address"));
                cs.setEmail(rs.getString("Email"));
                cs.setHireDate(rs.getDate("HireDate"));
                cs.setStatus(rs.getBoolean("Status"));
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setName(rs.getString("NameRoom"));
                room.setFloor(rs.getInt("Floor"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                room.setRoomType(rt);
                cs.setRoom(room);
                Account ac = new Account();
                ac.setId(rs.getInt("AccountId"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                cs.setAccount(ac);
                list.add(cs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Customer getCustomerById(int id){
        String sql = "select c.Id,c.[Name],c.DateOfBirth,c.Gender,c.PhoneNumber,c.IdentityCard,c.[Address],c.Email,c.HireDate,c.[Status],\n" +
                    "c.RoomId,r.[Name] as NameRoom,r.[Floor],r.[Status] as statusRoom,r.TypeId,\n" +
                    "c.AccountId,a.Username,a.[Password]\n" +
                    "from Customer as c \n" +
                    "inner join Room as r on c.RoomId = r.Id\n" +
                    "inner join Account as a on c.AccountId = a.Id\n"+
                    "where c.Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Customer cs = new Customer();
                cs.setId(rs.getInt("Id"));
                cs.setName(rs.getString("Name"));
                cs.setDob(rs.getDate("DateOfBirth"));
                cs.setGender(rs.getBoolean("Gender"));
                cs.setPhone(rs.getString("PhoneNumber"));
                cs.setIdentity(rs.getString("IdentityCard"));
                cs.setAddress(rs.getString("Address"));
                cs.setEmail(rs.getString("Email"));
                cs.setHireDate(rs.getDate("HireDate"));
                cs.setStatus(rs.getBoolean("Status"));
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setName(rs.getString("NameRoom"));
                room.setFloor(rs.getInt("Floor"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                room.setRoomType(rt);
                cs.setRoom(room);
                Account ac = new Account();
                ac.setId(rs.getInt("AccountId"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                cs.setAccount(ac);
                return cs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Customer> getListCustomerPaging(ArrayList<Customer> list,int start,int end){
        ArrayList<Customer> listCustomer = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listCustomer.add(list.get(i));
        }
        return listCustomer;
    }
    
    public void insertCustomer(Customer cs){
        String sql = "INSERT INTO [dbo].[Customer]\n" +
                    "           ([Name]\n" +
                    "           ,[Gender]\n" +
                    "           ,[DateOfBirth]\n" +
                    "           ,[PhoneNumber]\n" +
                    "           ,[IdentityCard]\n" +
                    "           ,[Address]\n" +
                    "           ,[Email]\n" +
                    "           ,[RoomId]\n" +
                    "           ,[HireDate]\n" +
                    "           ,[Status]\n" +
                    "           ,[AccountId])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cs.getName());
            stm.setBoolean(2, cs.isGender());
            stm.setDate(3, cs.getDob());
            stm.setString(4, cs.getPhone());
            stm.setString(5, cs.getIdentity());
            stm.setString(6, cs.getAddress());
            stm.setString(7, cs.getEmail());
            stm.setInt(8, cs.getRoom().getId());
            stm.setDate(9, cs.getHireDate());
            stm.setBoolean(10, true);
            stm.setInt(11, cs.getAccount().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCustomerInLast(){
        String sql = "select top(1) Id from Customer\n" +
                    "order by Id desc";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt("Id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public void updateStatusCustomerToFalse(int id){
        String sql = "update Customer set [Status] = 0\n" +
                    "where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Customer getCustomerByAccountId(int id){
        String sql = "select c.Id,c.[Name],c.DateOfBirth,c.Gender,c.PhoneNumber,c.IdentityCard,c.[Address],c.Email,c.HireDate,c.[Status],\n" +
                    "c.RoomId,r.[Name] as NameRoom,r.[Floor],r.[Status] as statusRoom,r.TypeId,\n" +
                    "c.AccountId,a.Username,a.[Password]\n" +
                    "from Customer as c \n" +
                    "inner join Room as r on c.RoomId = r.Id\n" +
                    "inner join Account as a on c.AccountId = a.Id\n"+
                    "where a.Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Customer cs = new Customer();
                cs.setId(rs.getInt("Id"));
                cs.setName(rs.getString("Name"));
                cs.setDob(rs.getDate("DateOfBirth"));
                cs.setGender(rs.getBoolean("Gender"));
                cs.setPhone(rs.getString("PhoneNumber"));
                cs.setIdentity(rs.getString("IdentityCard"));
                cs.setAddress(rs.getString("Address"));
                cs.setEmail(rs.getString("Email"));
                cs.setHireDate(rs.getDate("HireDate"));
                cs.setStatus(rs.getBoolean("Status"));
                Room room = new Room();
                room.setId(rs.getInt("RoomId"));
                room.setName(rs.getString("NameRoom"));
                room.setFloor(rs.getInt("Floor"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                room.setRoomType(rt);
                cs.setRoom(room);
                Account ac = new Account();
                ac.setId(rs.getInt("AccountId"));
                ac.setUsername(rs.getString("Username"));
                ac.setPassword(rs.getString("Password"));
                cs.setAccount(ac);
                return cs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void editCustomer(Customer cs){
        String sql = "update Customer set [Name] = ?,\n" +
"					Gender = ?,\n" +
"					DateOfBirth = ?,\n" +
"					PhoneNumber = ?,\n" +
"					IdentityCard = ?,\n" +
"					[Address] = ?,\n" +
"					Email = ?\n" +
"                   where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cs.getName());
            stm.setBoolean(2, cs.isGender());
            stm.setDate(3, cs.getDob());
            stm.setString(4, cs.getPhone());
            stm.setString(5, cs.getIdentity());
            stm.setString(6, cs.getAddress());
            stm.setString(7, cs.getEmail());
            stm.setInt(8, cs.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
