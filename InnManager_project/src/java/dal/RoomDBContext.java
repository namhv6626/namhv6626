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
import model.Room;
import model.RoomType;

/**
 *
 * @author firem
 */
public class RoomDBContext extends DBContext {

    public ArrayList<Room> getListRoom() {
        String sql = "select r.Id,r.[Name],r.[Floor],r.[Status],t.Id as TypeId,t.[Name] as TypeName,t.Price,t.Area,t.Quantity\n"
                + "from Room as r inner join RoomType as t\n"
                + "on r.TypeId = t.Id";
        ArrayList<Room> listRoom = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setFloor(rs.getInt("Floor"));
                room.setStatus(rs.getBoolean("Status"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                rt.setName(rs.getString("TypeName"));
                rt.setPrice(rs.getDouble("Price"));
                rt.setArea(rs.getDouble("Area"));
                rt.setQuantity(rs.getInt("Quantity"));
                room.setRoomType(rt);
                listRoom.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoom;
    }

    public ArrayList<Room> getListRoomByCondition(int type, int floor, int status,String search) {
        String sql = "select r.Id,r.[Name],r.[Floor],r.[Status],t.Id as TypeId,t.[Name] as TypeName,t.Price,t.Area,t.Quantity\n"
                + "from Room as r inner join RoomType as t\n"
                + "on r.TypeId = t.Id\n"
                + "where r.[Name] like ? ";
        if(type > -1){
            sql += "and r.TypeId = " + type + " ";
        }
        if(floor > -1){
            sql += "and r.[Floor] = " + floor + " ";
        }
        if(status > -1){
            sql += "and r.[Status] = " + status + " ";
        }
        sql += "\norder by r.[Name] ASC";
        ArrayList<Room> listRoom = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setFloor(rs.getInt("Floor"));
                room.setStatus(rs.getBoolean("Status"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                rt.setName(rs.getString("TypeName"));
                rt.setPrice(rs.getDouble("Price"));
                rt.setArea(rs.getDouble("Area"));
                rt.setQuantity(rs.getInt("Quantity"));
                room.setRoomType(rt);
                listRoom.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoom;
    }
    
    public ArrayList<Room> getListRoomByCondition1(int type, int floor, int status) {
        String sql = "select r.Id,r.[Name],r.[Floor],r.[Status],t.Id as TypeId,t.[Name] as TypeName,t.Price,t.Area,t.Quantity\n"
                + "from Room as r inner join RoomType as t\n"
                + "on r.TypeId = t.Id\n";
        if (type > -1) {
            sql += "where r.TypeId = " + type + " ";
            if (floor > -1) {
                sql += "and r.[Floor] = " + floor + " ";
            }
            if (status > -1) {
                sql += "and r.[Status] = " + status + " ";
            }
        } else {
            if (floor > -1) {
                sql += "where r.[Floor] = " + floor + " ";
                if (status > -1) {
                    sql += "and r.[Status] = " + status + " ";
                }
            }
            else{
                if (status > -1) {
                    sql += "where r.[Status] = " + status + " ";
                }
            }
        }
        ArrayList<Room> listRoom = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setFloor(rs.getInt("Floor"));
                room.setStatus(rs.getBoolean("Status"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                rt.setName(rs.getString("TypeName"));
                rt.setPrice(rs.getDouble("Price"));
                rt.setArea(rs.getDouble("Area"));
                rt.setQuantity(rs.getInt("Quantity"));
                room.setRoomType(rt);
                listRoom.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoom;
    }

    public ArrayList<Integer> getFloorRoom() {
        String sql = "select [Floor] from Room\n"
                + "group by [Floor]";
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("Floor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public void insertRoom(Room rm,int typeId){
        String sql = "insert into Room(\n" +
                        "	[Name],\n" +
                        "	[Floor],\n" +
                        "	TypeId,\n" +
                        "	[Status])\n" +
                        "values\n" +
                        "	(?,\n" +
                        "	?,\n" +
                        "	?,\n" +
                        "	?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, rm.getName());
            stm.setInt(2, rm.getFloor());
            stm.setInt(3, typeId);
            stm.setBoolean(4, false);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(stm != null)
            {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection !=null)
            {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean getRoomByName(String name){
        String sql = "select Id,[Name],[Floor],TypeId,[Status] from Room\n" +
                    "where [Name] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Room getRoomById(int id){
        String sql = "select r.Id,r.[Name],r.[Floor],r.[Status],t.Id as TypeId,t.[Name] as TypeName,t.Price,t.Area,t.Quantity\n"
                + "from Room as r inner join RoomType as t\n"
                + "on r.TypeId = t.Id\n"
                + "where r.Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setFloor(rs.getInt("Floor"));
                room.setStatus(rs.getBoolean("Status"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                rt.setName(rs.getString("TypeName"));
                rt.setPrice(rs.getDouble("Price"));
                rt.setArea(rs.getDouble("Area"));
                rt.setQuantity(rs.getInt("Quantity"));
                room.setRoomType(rt);
                return room;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Room> getListRoomPaging(ArrayList<Room> list,int start,int end){
        ArrayList<Room> listRoom = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listRoom.add(list.get(i));
        }
        return listRoom;
    }
    
    public void updateRoomForActive(int id){
        String sql = "update Room set [Status] = 1 where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateRoomForDeactive(int id){
        String sql = "update Room set [Status] = 0 where Id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Room> getListRoomByRoomTypeId(int id) {
        String sql = "select r.Id,r.[Name],r.[Floor],r.[Status],t.Id as TypeId,t.[Name] as TypeName,t.Price,t.Area,t.Quantity\n"
                + "from Room as r inner join RoomType as t\n"
                + "on r.TypeId = t.Id\n"
                + "where t.Id = ? and r.[Status] = 1 ";
        ArrayList<Room> listRoom = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("Id"));
                room.setName(rs.getString("Name"));
                room.setFloor(rs.getInt("Floor"));
                room.setStatus(rs.getBoolean("Status"));
                RoomType rt = new RoomType();
                rt.setId(rs.getInt("TypeId"));
                rt.setName(rs.getString("TypeName"));
                rt.setPrice(rs.getDouble("Price"));
                rt.setArea(rs.getDouble("Area"));
                rt.setQuantity(rs.getInt("Quantity"));
                room.setRoomType(rt);
                listRoom.add(room);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoom;
    }
    
    public void deleteRoomByRoomTypeId(int id){
        String sql = "delete Room where TypeId = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
