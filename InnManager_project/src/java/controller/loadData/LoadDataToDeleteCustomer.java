/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.loadData;

import dal.ContractDBContext;
import dal.CustomerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contract;
import model.Customer;

/**
 *
 * @author firem
 */
@WebServlet(name = "LoadDataToDeleteCustomer", urlPatterns = {"/dataDeleteCustomer"})
public class LoadDataToDeleteCustomer extends HttpServlet {

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
        String idString = request.getParameter("idPayment");
        int id = Integer.parseInt(idString);
        CustomerDBContext customerSql = new CustomerDBContext();
        Customer customer = customerSql.getCustomerById(id);
        ArrayList<Customer> listCustomer = customerSql.getListCustomer();
        ContractDBContext contractSql = new ContractDBContext();
        Contract contract = contractSql.getContractByIdRoom(customer.getRoom().getId(), 1);
        PrintWriter out = response.getWriter();
        out.println("<form id=\"model-change\" action=\"delete\" method=\"POST\">\n" +
        "                <h2 style=\"text-align: center;\">Xóa khách hàng</h2>\n" +
        "                <p>Phòng: " + customer.getRoom().getName() + "</p>");
        if(customer.getId() != contract.getCustomer().getId()){
            out.println("<p>Chức vụ: Người ở trọ</p>\n" +
        "                </br>\n" +
        "                </br>\n" +
        "                </br>\n" +
        "                </br>\n" +
        "                <p>Xác nhận xóa khách hàng này?</p>\n" +
        "                <div class=\"button-submit-form\">\n" +
        "                    <input type=\"hidden\" name=\"idCustomer\" value=\"" + customer.getId() + "\">\n" +
        "                    <input class=\"btn btn-primary\" type=\"submit\" value=\"Xác nhận\">\n" +
        "                </div>");
        }else{
            ArrayList<Customer> listCustomerByRoomId = customerSql.getListCustomerByRoomId(customer.getRoom().getId());
            if(listCustomerByRoomId.size() > 1){
                out.println("<p>Chức vụ: Người đại diện</p>\n" +
            "                <p>Thay đổi người đại diện</p>");
                for (Customer cus : listCustomerByRoomId) {
                    if(cus.getId() != customer.getId()){
                        out.println("<input type=\"radio\" name=\"idCustomerChange\" value=\"" + cus.getId() + "\">" + cus.getName() + " </br>");
                    }
                }
                out.println("</br>\n" +
            "                <p>Xác nhận xóa khách hàng này?</p>\n" +
            "                <div class=\"button-submit-form\">\n" +
            "                    <input type=\"hidden\" name=\"idCustomer\" value=\"" + customer.getId() + "\">\n" +
            "                    <input class=\"btn btn-primary\" type=\"submit\" value=\"Xác nhận\">\n" +
            "                </div>");
            }else{
                out.println("<p>Chức vụ: Người đại diện</p>\n" +
            "                </br>\n" +
            "                </br>\n" +
            "                </br>\n" +
            "                </br>\n" +
            "                <p>Thực hiện checkout phòng?</p>\n" +
            "                <div class=\"button-submit-form\">\n" +
            "                    <a class=\"btn btn-danger button-checkOut\" href=\"../room/checkout?id=" + contract.getId() + "\" role=\"button\">\n" +
            "                        <span>Check out</span>\n" +
            "                    </a>\n" +
            "                </div>");
            }
        }
        out.println("</form>");
        
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
