/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import controller.base.BaseUserController;
import dal.ContractDBContext;
import dal.CustomerDBContext;
import dal.PaymentHistoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Contract;
import model.Customer;
import model.Payment;
import model.PaymentHistory;

/**
 *
 * @author firem
 */
public class RevenueUserController extends BaseUserController {

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
        Account ac =(Account) request.getSession().getAttribute("account");
        ArrayList<PaymentHistory> listPayment = new ArrayList<>();
        CustomerDBContext customerSql = new CustomerDBContext();
        Customer customer = customerSql.getCustomerByAccountId(ac.getId());
        
        String fromDateString = request.getParameter("fromDate");
        String toDateString = request.getParameter("toDate");
        String searchString = request.getParameter("search");
//        Date fromDate = null;
//        Date toDate = null;
        if(fromDateString == null){
            fromDateString = "";
        }
        if(toDateString == null){
            toDateString = "";
        }
        if(searchString == null){
            searchString = "";
        }
        PaymentHistoryDBContext paymentHistorySql = new PaymentHistoryDBContext();
        ArrayList<PaymentHistory> listPaymentHistory = paymentHistorySql.getListPaymentHistoryByCondition(fromDateString, toDateString, searchString);
        
        
        if(customer.isStatus()){
            ContractDBContext contractSql = new ContractDBContext();
            Contract contract = contractSql.getContractByIdRoom(customer.getRoom().getId(), 1);
            for (PaymentHistory ph : listPaymentHistory) {
                if(ph.getPayment().getContract().getId() == contract.getId()){
                    listPayment.add(ph);
                }
            }
        }
               
        request.setAttribute("listPaymentHistory", listPayment);
        request.setAttribute("fromDate", fromDateString);
        request.setAttribute("toDate", toDateString);
        request.setAttribute("search", searchString);
        request.getRequestDispatcher("../viewUser/revenue.jsp").forward(request, response);
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
