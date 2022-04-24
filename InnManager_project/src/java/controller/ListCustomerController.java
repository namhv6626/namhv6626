/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.CustomerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author firem
 */
public class ListCustomerController extends BaseController {

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
        //lay thong tin tu web
        String searchString = request.getParameter("search");
        if(searchString == null){
            searchString = "";
        }
        String statusString = request.getParameter("status");
        if(statusString == null){
            statusString = "-1";
        }
        int status = Integer.parseInt(statusString);
        
        //lay thong tin tu data
        CustomerDBContext customerSql = new CustomerDBContext();
        ArrayList<Customer> listCustomer = customerSql.getListCustomerByCondition(status, searchString);
        
        //phan trang
        String indexPageString = request.getParameter("page");
        int pageSize = 10;
        int size = listCustomer.size();
        int numberPage = (size % pageSize == 0) ? (size / pageSize) : ((size/pageSize) + 1);
        int indexPage;
        if(indexPageString == null){
            indexPage = 1;
        }else{
            indexPage = Integer.parseInt(indexPageString);
        }
        int start =(indexPage - 1) * pageSize;
        int end = Math.min(indexPage * pageSize, size);
        ArrayList<Customer> listCustomerPaging = customerSql.getListCustomerPaging(listCustomer, start, end);
        
        request.setAttribute("listCustomer", listCustomerPaging);
        request.setAttribute("indexPage", indexPage);
        request.setAttribute("numberPage", numberPage);
        request.setAttribute("status", status);
        request.setAttribute("search", searchString);
        
        request.getRequestDispatcher("../../view/listCustomer.jsp").forward(request, response);
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
