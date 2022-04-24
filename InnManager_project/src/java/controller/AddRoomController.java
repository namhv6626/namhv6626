/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.RoomDBContext;
import dal.RoomTypeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Room;
import model.RoomType;
import validator.InputValidation;

/**
 *
 * @author firem
 */
public class AddRoomController extends BaseController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomTypeDBContext roomTyprSql = new RoomTypeDBContext();
        ArrayList<RoomType> listRoomType = roomTyprSql.getListRoomType();
        request.setAttribute("listRoomType", listRoomType);
        request.getRequestDispatcher("../../view/addRoom.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoomTypeDBContext roomTyprSql = new RoomTypeDBContext();
        ArrayList<RoomType> listRoomType = roomTyprSql.getListRoomType();
        request.setAttribute("listRoomType", listRoomType);
        
        String nameString = request.getParameter("name");
        String floorString = request.getParameter("floor");
        String typeString = request.getParameter("type");
        int successful = 0;
        
        RoomDBContext roomSql = new RoomDBContext();
        if(roomSql.getRoomByName(nameString)){
            String errorName = "Tên phòng đã tồn tại";
            request.setAttribute("errorName", errorName);
        }else if(!InputValidation.isInteger(floorString)){
            String errorFloor = "Giá trị không phải là số";
            request.setAttribute("errorFloor", errorFloor);
        }else if(!floorString.equals(nameString.charAt(1) + "")){
            String errorSimilar = "Tầng không phù hợp với tên phòng";
            request.setAttribute("errorSimilar", errorSimilar);
        }else{
            int floor = Integer.parseInt(floorString);
            int type = Integer.parseInt(typeString);
            Room rm = new Room();
            rm.setName(nameString);
            rm.setFloor(floor);
            roomSql.insertRoom(rm, type);
            successful = 1;
        }
        
        request.setAttribute("successful", successful);
        request.getRequestDispatcher("../../view/addRoom.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
