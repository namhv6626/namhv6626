/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.loadData;

import dal.PaymentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Payment;
import model.ServiceDetail;

/**
 *
 * @author firem
 */
@WebServlet(name = "LoadDataToAddBill", urlPatterns = {"/addBill"})
public class LoadDataToAddBill extends HttpServlet {

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
        ArrayList<Payment> listPayment = paymentSql.getListPaymentToPay();
        double total = 0;
        double totalService = 0;
        PrintWriter out = response.getWriter();
        for (Payment pm : listPayment) {
            if(pm.getId() == id){
//                out.println("<button class=\"btn_close\" type=\"button\">&times;</button>");
                out.println("<div id=\"model-change\">\n" +
        "                    <h2 style=\"text-align: center;\">Hóa đơn</h2>\n" +
        "                    <p>Phòng: " + pm.getContract().getRoom().getName() + "</p>\n" +
        "                    <p>Ngày: " + pm.getFromDate() + "</p>\n" +
        "                    <table class=\"table\">\n" +
        "                        <tr>\n" +
        "                            <th>Tên dịch vụ</th>\n" +
        "                            <th>Đơn vị</th>\n" +
        "                            <th>Đơn giá</th>\n" +
        "                            <th>Số lượng</th>\n" +
        "                            <th>Thành tiền</th>\n" +
        "                        </tr>");
                for (ServiceDetail sd : pm.getBill().getListService()) {
                    totalService += sd.getService().getPrice() * sd.getQuantity();
                    out.println("<tr>\n" +
        "                            <td>" + sd.getService().getName() + "</td>\n" +
        "                            <td>" + sd.getService().getUnit() + "</td>\n" +
        "                            <td>" + sd.getService().getPrice() + "</td>\n" +
        "                            <td>" + sd.getQuantity() + "</td>\n" +
        "                            <td>" + sd.getService().getPrice() * sd.getQuantity()+ "</td>\n" +
        "                        </tr>");
                }
                out.println("</table>");
                total = pm.getContract().getRoom().getRoomType().getPrice() + totalService;
                out.println("<table class=\"table\">\n" +
        "                        <tr>\n" +
        "                            <td>Tiền phòng</td>\n" +
        "                            <td>" + pm.getContract().getRoom().getRoomType().getPrice() + "</td>\n" +
        "                        </tr>\n" +
        "                        <tr>\n" +
        "                            <td>Tổn tiền dịch vụ</td>\n" +
        "                            <td>" + totalService + "</td>\n" +
        "                        </tr>\n" +
        "                        <tr>\n" +
        "                            <td>Thành tiền</td>\n" +
        "                            <td>" + total + "</td>\n" +
        "                        </tr>\n" +
        "                    </table>");
                out.println("</div>");           
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
