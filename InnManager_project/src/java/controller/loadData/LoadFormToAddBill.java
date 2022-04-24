/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.loadData;

import dal.PaymentDBContext;
import dal.ServiceTypeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Payment;
import model.ServiceType;

/**
 *
 * @author firem
 */
@WebServlet(name = "LoadFormToAddBill", urlPatterns = {"/loadForm"})
public class LoadFormToAddBill extends HttpServlet {

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
        PaymentDBContext paymentSql = new PaymentDBContext();
        ArrayList<Payment> listPayment = paymentSql.getListPaymentToCreate();
        ServiceTypeDBContext serviceTypeSql = new ServiceTypeDBContext();
        ArrayList<ServiceType> listServiceType = serviceTypeSql.getListServiceType();
        PrintWriter out = response.getWriter();
        for (Payment pm : listPayment) {
            if(pm.getId() == id){
                out.println("<form id=\"model-change\" action=\"bill/collect\" method=\"POST\">\n" +
            "                <h2 style=\"text-align: center;\">Hóa đơn</h2>\n" +
            "                <p>Phòng: " + pm.getContract().getRoom().getName() + "</p>\n" +
            "                <p>Ngày bắt đầu: " + pm.getFromDate() + "</p>\n" +
            "                <table class=\"table\">\n" +
            "                    <tr>\n" +
            "                        <th class=\"text-center align-middle\">Tên dịch vụ</th>\n" +
            "                        <th class=\"text-center align-middle\">Đơn vị</th>\n" +
            "                        <th class=\"text-center align-middle\">Đơn giá</th>\n" +
            "                        <th class=\"text-center align-middle\">Số lượng</th>\n" +
            "                    </tr>");
                for (ServiceType st : listServiceType) {
                    out.println("<tr>\n" +
            "                        <td class=\"text-center align-middle\">" + st.getName() + "</td>\n" +
            "                        <td class=\"text-center align-middle\">" + st.getUnit() + "</td>\n" +
            "                        <td class=\"text-center align-middle\">" + (long)st.getPrice() + "</td>\n" +
            "                        <td class=\"text-center align-middle\"><input type=\"text\" name=\"service" + st.getId() + "\" class=\"input-number\" value=\"0\"></td>\n" +
            "                    </tr>");
                }
                out.println("</table>");
                out.println("<table class=\"table\">\n" +
            "                    <tr>\n" +
            "                        <td>Tiền phòng</td>\n" +
            "                        <td>" + pm.getContract().getRoom().getRoomType().getPriceLong() + "</td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "                <div class=\"button-submit-form\">\n" +
            "                    <input type=\"hidden\" name=\"idPayment\" value=\"" + pm.getId() + "\">\n"+
            "                    <input class=\"btn btn-primary\" type=\"submit\" value=\"Xác nhận\">\n" +
            "                </div>");
                out.println("</form>");
            
            }
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
