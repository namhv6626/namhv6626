/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.BillDBContext;
import dal.ServiceTypeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bill;
import model.ServiceType;
import validator.InputValidation;

/**
 *
 * @author firem
 */
public class SettingServiceController extends BaseController {

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
        ServiceTypeDBContext serviceTypeSql = new ServiceTypeDBContext();
        ArrayList<ServiceType> listServiceType = serviceTypeSql.getListServiceType();

        request.setAttribute("listServiceType", listServiceType);
        request.getRequestDispatcher("../../view/settingService.jsp").forward(request, response);
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
        ServiceTypeDBContext serviceTypeSql = new ServiceTypeDBContext();
        ArrayList<ServiceType> listServiceType = serviceTypeSql.getListServiceType();
        request.setAttribute("listServiceType", listServiceType);

        int successfull = 0;
        for (ServiceType st : listServiceType) {
            String priceString = request.getParameter("service" + st.getId());
            double price = Double.parseDouble(priceString);
            serviceTypeSql.updatePriceService(st.getId(), price);
        }
        successfull = 1;
        request.setAttribute("successfull", successfull);
        request.getRequestDispatcher("../../view/settingService.jsp").forward(request, response);

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
