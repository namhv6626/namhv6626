/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.AccountDBContext;
import dal.ConductDBContext;
import dal.ContractDBContext;
import dal.CustomerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.ConductDetail;
import model.Contract;
import model.Customer;

/**
 *
 * @author firem
 */
public class InformationRoomController extends BaseController {

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
        Cookie[] cookies = request.getCookies();
        String errorAddCustomer = null;
        String errorCheckout = null;
        if (cookies != null)//not login, some cookies
        {
            for (Cookie cooky : cookies) {
                System.out.println(cooky.getName());
                System.out.println(cooky.getValue());
                if (cooky.getName().equals("errorAddCustomer")) {
                    errorAddCustomer = "phòng hết chỗ trống";
                }
                if (cooky.getName().equals("errorCheckout")) {
                    errorCheckout = "phòng chưa trả tiền tháng";
                }
            }
        }
        
        //lay thong tin tu web
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        String statusString = request.getParameter("status");
        int status = Integer.parseInt(statusString);

        //lay thong tin tu data
        ContractDBContext contractSql = new ContractDBContext();
        Contract contract = contractSql.getContractByIdRoom(id, status);
        ConductDBContext conductSql = new ConductDBContext();
        ArrayList<ConductDetail> listConductDetail = conductSql.getListConducttDetail(contract.getListConduct());
        CustomerDBContext customerSql = new CustomerDBContext();
        ArrayList<Customer> listCustomer = customerSql.getListCustomerByRoomId(id);

        request.setAttribute("contract", contract);
        request.setAttribute("listConductDetail", listConductDetail);
        request.setAttribute("listCustomer", listCustomer);
        request.setAttribute("idRoom", id);
        request.setAttribute("statusRoom", status);
        request.setAttribute("errorAddCustomer", errorAddCustomer);
        request.setAttribute("errorCheckout", errorCheckout);    
        request.getRequestDispatcher("../../view/informationRoom.jsp").forward(request, response);
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
