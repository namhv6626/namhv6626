/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.ContractDBContext;
import dal.RoomDBContext;
import dal.RoomTypeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contract;
import model.Room;
import model.RoomType;

/**
 *
 * @author firem
 */
public class ListRoomController extends BaseController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay thong tin tu data
        RoomTypeDBContext roomTypeSql = new RoomTypeDBContext();
        ArrayList<RoomType> listRoomType = roomTypeSql.getListRoomType();
        request.setAttribute("listRoomType", listRoomType);
        RoomDBContext roomSql = new RoomDBContext();
        ArrayList<Integer> listFloor = roomSql.getFloorRoom();
        request.setAttribute("listFloor", listFloor);
        //lay thong tin tu web
        String searchString = request.getParameter("search");
        if(searchString == null){
            searchString = "";
        }
        String typeString = request.getParameter("type");
        String floorString = request.getParameter("floor");
        String statusString = request.getParameter("status");
        if(typeString == null) typeString = "-1";
        if(floorString == null) floorString = "-1";
        if(statusString == null) statusString = "-1";
        int type = Integer.parseInt(typeString);
        int floor = Integer.parseInt(floorString);
        int status = Integer.parseInt(statusString);
        
        ArrayList<Room> listRoom = roomSql.getListRoomByCondition(type, floor, status,searchString);
        //phan trang
        String indexPageString = request.getParameter("page");
        int pageSize = 12;
        int size = listRoom.size();
        int numberPage = (size % pageSize == 0) ? (size / pageSize) : ((size/pageSize) + 1);
        int indexPage;
        if(indexPageString == null){
            indexPage = 1;
        }else{
            indexPage = Integer.parseInt(indexPageString);
        }
        int start =(indexPage - 1) * pageSize;
        int end = Math.min(indexPage * pageSize, size);
        ArrayList<Room> listRoomPaging = roomSql.getListRoomPaging(listRoom, start, end);
        
        ContractDBContext contractSql = new ContractDBContext();
        ArrayList<Contract> listContract = contractSql.getListContract();
        
        request.setAttribute("listContract", listContract);
        request.setAttribute("listRoom", listRoomPaging);
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("type", type);
        request.setAttribute("floor", floor);
        request.setAttribute("status", status);
        request.setAttribute("search", searchString);
        
        request.getRequestDispatcher("../../view/listRoom.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
