/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author firem
 */
public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("view/login.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        AccountDBContext db = new AccountDBContext();
        String userLogin = request.getParameter("user");
        String passLogin = request.getParameter("pass");
        Account ac = db.getAccount(userLogin, passLogin);
        if (ac != null) {
            request.getSession().setAttribute("account", ac);
//            request.getSession().setMaxInactiveInterval(1800);
//            Cookie c_user = new Cookie("username", userLogin);
//            Cookie c_pass = new Cookie("password", passLogin);
//            c_user.setMaxAge(3600);
//            c_pass.setMaxAge(3600);
//            response.addCookie(c_user);
//            response.addCookie(c_pass);
            if (ac.getUsername().equals("admin") && ac.getPassword().equals("123")) {
                response.sendRedirect("admin/home");
            } else {
                response.sendRedirect("user/notification");
            }
        } else {
            String errorMessage = "Username or Password wrong";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
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
