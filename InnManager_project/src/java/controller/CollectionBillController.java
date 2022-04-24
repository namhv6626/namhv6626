/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BillDBContext;
import dal.PaymentDBContext;
import dal.PaymentHistoryDBContext;
import dal.ServiceDetailDBContext;
import dal.ServiceTypeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import model.Payment;
import model.PaymentHistory;
import model.ServiceType;

/**
 *
 * @author firem
 */
public class CollectionBillController extends HttpServlet {

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
        String idPaymentString  = request.getParameter("idPayment");
        int idPayment = Integer.parseInt(idPaymentString);
        String idBillString = request.getParameter("idBill");
        int idBill = Integer.parseInt(idBillString);
        
        PaymentDBContext paymentSql = new PaymentDBContext();
        Payment payment = paymentSql.getPaymentByIdNotNull(idPayment);
        
        BillDBContext billSql = new BillDBContext();
        Bill bill = billSql.getBillByIdBill(idBill);
        
        PaymentHistory ph = new PaymentHistory();
        ph.setPayment(payment);
        ph.setBill(bill);
        ph.setFromDate(payment.getFromDate());
        ph.setToDate(payment.getToDate());
        PaymentHistoryDBContext paymentHistorySql = new PaymentHistoryDBContext();
        paymentHistorySql.insertPaymentHistory(ph);
        
        Date fromDate = payment.getToDate();
        Date toDate = Date.valueOf(fromDate.toLocalDate().plusMonths(1));
        payment.setFromDate(fromDate);
        payment.setToDate(toDate);
        paymentSql.updateNewCurrentPayment(payment);
        
        response.sendRedirect("add");
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
        String idString  = request.getParameter("idPayment");
        int id = Integer.parseInt(idString);
        
        BillDBContext billSql = new BillDBContext();
        billSql.insertBill();
        int idBill = billSql.getBillInLast();
        
        PaymentDBContext paymentSql = new PaymentDBContext();
        paymentSql.updateCurrentBillId(idBill, id);
        
        ServiceTypeDBContext serviceTypeSql = new ServiceTypeDBContext();
        ArrayList<ServiceType> listServiceType = serviceTypeSql.getListServiceType();
        ServiceDetailDBContext serviceDetailSql = new ServiceDetailDBContext();
        for (ServiceType st : listServiceType) {
            String numberString = request.getParameter("service" + st.getId());
            int number = Integer.parseInt(numberString);
            if(number != 0){
                serviceDetailSql.insertServiceBill(idBill, st.getId(),st.getPrice(), number);
            }
        }
        
        billSql.updatePrice(idBill);
        
        response.sendRedirect("../notification");
        
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
