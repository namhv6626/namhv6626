/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.loadData;

import dal.PaymentHistoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PaymentHistory;
import model.ServiceDetail;

/**
 *
 * @author firem
 */
@WebServlet(name = "LoadDataPaymentHistory", urlPatterns = {"/loadPaymentHistory"})
public class LoadDataPaymentHistory extends HttpServlet {

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
        String idPaymentString = request.getParameter("idPayment");
        int idPayment = Integer.parseInt(idPaymentString);
        PaymentHistoryDBContext paymentHistorySql = new PaymentHistoryDBContext();
        ArrayList<PaymentHistory> listPaymentHistory = paymentHistorySql.getListPaymentHistory();
        PrintWriter out = response.getWriter();
        double total = 0;
        double totalService = 0;
        for (PaymentHistory ph : listPaymentHistory) {
            if(ph.getId() == idPayment){
                out.println("<div id=\"model-change\">\n" +
            "                    <h2 style=\"text-align: center;\">Hóa đơn</h2>\n" +
            "                    <p>Phòng: " + ph.getPayment().getContract().getRoom().getName() + "</p>\n" +
            "                    <p>Ngày: " + ph.getFromDate() + "</p>");
                out.println("<table class=\"table\">\n" +
            "                    <tr>\n" +
            "                        <th class=\"text-center align-middle\">Tên dịch vụ</th>\n" +
            "                        <th class=\"text-center align-middle\">Đơn vị</th>\n" +
            "                        <th class=\"text-center align-middle\">Đơn giá</th>\n" +
            "                        <th class=\"text-center align-middle\">Số lượng</th>\n" +
            "                    </tr>");
                for (ServiceDetail sd : ph.getBill().getListService()) {
                    totalService += sd.getQuantity() * sd.getPrice();
                    out.println("<tr>\n" +
            "                        <td class=\"text-center align-middle\">" + sd.getService().getName() + "</td>\n" +
            "                        <td class=\"text-center align-middle\">" + sd.getService().getUnit() + "</td>\n" +
            "                        <td class=\"text-center align-middle\">" + sd.getPrice() + "</td>\n" +
            "                        <td class=\"text-center align-middle\">" + sd.getQuantity() + "</td>\n" +
            "                    </tr>");
                }
                out.println("</table>");
                total += ph.getPayment().getContract().getContractDetail().getPrice() + totalService;
                out.println("<table class=\"table\">\n" +
            "                    <tr>\n" +
            "                        <td>Tiền phòng</td>\n" +
            "                        <td>" + ph.getPayment().getContract().getContractDetail().getPrice() + "</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td>Tiền dịch vụ</td>\n" +
            "                        <td>" + totalService + "</td>\n" +
            "                    </tr>\n" +
            "                    <tr>\n" +
            "                        <td>Thanh toán</td>\n" +
            "                        <td>" + total + "</td>\n" +
            "                    </tr>\n" +
            "                </table>\n" +
            "            </div>");
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
