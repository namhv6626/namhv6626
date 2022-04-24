/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.ConductDBContext;
import dal.ContractDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Conduct;
import model.Contract;

/**
 *
 * @author firem
 */
public class SettingConductController extends BaseController {

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
        ConductDBContext conductSql = new ConductDBContext();
        ArrayList<Conduct> listConduct = conductSql.getListConduct();
        
        request.setAttribute("listConduct", listConduct);
        request.getRequestDispatcher("../../view/settingConduct.jsp").forward(request, response);
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
        ConductDBContext conductSql = new ConductDBContext();
        ArrayList<Conduct> listConduct = conductSql.getListConduct();
        request.setAttribute("listConduct", listConduct);
        
        int successfull = 0;
        for (Conduct cd : listConduct) {
            String priceString = request.getParameter("conduct" + cd.getId());
            double price = Double.parseDouble(priceString);
            conductSql.updatePriceConduct(cd.getId(), price);
        }
        
        ContractDBContext contractSql = new ContractDBContext();
        ArrayList<Contract> listContract = contractSql.getListContract();
        for (Contract ct : listContract) {
            if(!ct.isStatus()){
                contractSql.updatePriceConduct(ct.getId());
            }
        }
        
        successfull = 1;
        request.setAttribute("successfull", successfull);
        request.getRequestDispatcher("../../view/settingConduct.jsp").forward(request, response);
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
