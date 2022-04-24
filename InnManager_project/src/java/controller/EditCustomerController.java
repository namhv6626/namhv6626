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
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author firem
 */
public class EditCustomerController extends BaseController {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditCustomerController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCustomerController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString); 
        
        CustomerDBContext customerSql = new CustomerDBContext();
        Customer customer = customerSql.getCustomerById(id);
        
        request.setAttribute("customer", customer);
        request.setAttribute("id", id);
        request.getRequestDispatcher("../../view/editCustomer.jsp").forward(request, response);
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
        String idString = request.getParameter("idCustomer");
        String nameString = request.getParameter("name");
        String genderString = request.getParameter("gender");
        String dobString = request.getParameter("dob");
        String phoneString = request.getParameter("phone");
        String identityString = request.getParameter("identity");
        String emailString = request.getParameter("email");
        String addressString = request.getParameter("address");
        
        int id = Integer.parseInt(idString);
        boolean gender = genderString.equals("1");
        Date dob = Date.valueOf(dobString);
        
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(nameString);
        customer.setGender(gender);
        customer.setDob(dob);
        customer.setPhone(phoneString);
        customer.setIdentity(identityString);
        customer.setAddress(addressString);
        customer.setEmail(emailString);
        
        CustomerDBContext customerSql = new CustomerDBContext();
        customerSql.editCustomer(customer);
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
