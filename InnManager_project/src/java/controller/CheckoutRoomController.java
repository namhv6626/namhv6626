/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ConductDBContext;
import dal.ContractDBContext;
import dal.CustomerDBContext;
import dal.PaymentDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Conduct;
import model.Contract;
import model.Customer;
import model.Payment;
import model.Room;

/**
 *
 * @author firem
 */
public class CheckoutRoomController extends HttpServlet {


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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        
        ContractDBContext contractSql = new ContractDBContext();
        Contract contract = contractSql.getContractById(id);
        ArrayList<Conduct> listConduct = contract.getListConduct();
        
        PaymentDBContext paymentSql = new PaymentDBContext();
        Payment payment = paymentSql.getPaymentByContractId(id);
        
        boolean flag = false;
        if (payment.getBill().getId() != 0) {
            String errorCheckout = "phong chua tra tien thang";
            Cookie c_errorCheckout = new Cookie("errorCheckout", errorCheckout);
            c_errorCheckout.setMaxAge(1);
            response.addCookie(c_errorCheckout);
            flag = true;
        }
        if (flag) {
            response.sendRedirect("../room/information?id=" + contract.getRoom().getId() + "&status=1");
        } else {
            request.setAttribute("contract", contract);
            request.setAttribute("listConduct", listConduct);
            request.getRequestDispatcher("../../view/checkoutRoom.jsp").forward(request, response);
        }
        
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idContractString = request.getParameter("idContract");
        int idContract = Integer.parseInt(idContractString);
        
        ContractDBContext contractSql = new ContractDBContext();
        Contract contract = contractSql.getContractById(idContract);
        ArrayList<Conduct> listConduct = contract.getListConduct();
        
        ConductDBContext conductSql = new ConductDBContext();
        for (Conduct cd : listConduct) {
            String check = request.getParameter("conduct" + cd.getId());
            if(check != null){
                String reason = request.getParameter("reason" + cd.getId());
                conductSql.updateConductService(idContract, cd.getId(), reason);
            }
        }
        
        contractSql.updatePriceConduct(idContract);
        
        CustomerDBContext customerSql = new CustomerDBContext();
        ArrayList<Customer> listCustomer = customerSql.getListCustomerByRoomId(contract.getRoom().getId());
        for (Customer cs : listCustomer) {
            customerSql.updateStatusCustomerToFalse(cs.getId());
        }
        
        RoomDBContext roomSql = new RoomDBContext();
        roomSql.updateRoomForDeactive(contract.getRoom().getId());
        
        PaymentDBContext paymentsql = new PaymentDBContext();
        Payment payment = paymentsql.getPaymentByContractId(idContract);
        paymentsql.updateStatusPayment(payment.getId());
        
        contractSql.updateStatusContract(idContract);
        
        response.sendRedirect("list");
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
