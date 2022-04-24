/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.loadData;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author firem
 */
@WebServlet(name = "LoadDataToDeleteRoomType", urlPatterns = {"/deleteRoomType"})
public class LoadDataToDeleteRoomType extends HttpServlet {

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
        String idString = request.getParameter("idRoomType");
        int id = Integer.parseInt(idString);
        PrintWriter out = response.getWriter();
        out.println("<form id=\"model-change\" action=\"delete\" method=\"Get\">\n" +
    "                    <h2 style=\"text-align: center;padding-right: 10px;\">Xóa kiểu phòng</h2>\n" +
    "                    </br>\n" +
    "                    </br>\n" +
    "                    </br>\n" +
    "                    </br>\n" +
    "                    <div class=\"button-submit-form\">\n" +
    "                        <input type=\"hidden\" name=\"id\" value=\"" + id + "\">\n" +
    "                        <input class=\"btn btn-primary\" type=\"submit\" value=\"Xác nhận\">\n" +
    "                    </div>\n" +
    "                </form>");
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
