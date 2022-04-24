/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.AccountDBContext;
import dal.ContractDBContext;
import dal.CustomerDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Contract;
import model.Customer;
import model.Room;

/**
 *
 * @author firem
 */
public class AddCustomerController extends BaseController {

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
        //lay thong tin tu web
        String idRoomString = request.getParameter("idRoom");
        int idRoom = Integer.parseInt(idRoomString);

        //lay thong tin tu database
        ContractDBContext contractSql = new ContractDBContext();
        Contract contract = contractSql.getContractByIdRoom(idRoom, 1);
        RoomDBContext roomSql = new RoomDBContext();
        Room room = roomSql.getRoomById(idRoom);
        CustomerDBContext customerDBContext = new CustomerDBContext();
        ArrayList<Customer> listCustomer = customerDBContext.getListCustomerByRoomId(idRoom);
        boolean flag = false;
        if (contract.getRoom().getRoomType().getQuantity() < listCustomer.size() + 1) {
            String errorAddCustomer = "phong da het cho trong";
            Cookie c_errorAddCustomer = new Cookie("errorAddCustomer", errorAddCustomer);
            c_errorAddCustomer.setMaxAge(1);
            response.addCookie(c_errorAddCustomer);
            flag = true;
        }
        if (flag) {
            response.sendRedirect("../room/information?id=" + idRoomString + "&status=1");
        } else {
            request.setAttribute("room", room);
            request.getRequestDispatcher("../../view/addCustomer.jsp").forward(request, response);
        }

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
        boolean flag = false;
        AccountDBContext accountSql = new AccountDBContext();
        ContractDBContext contractSql = new ContractDBContext();
        //lay thong tin tu web
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String nameCustomerString = request.getParameter("nameCustomer");
        String genderCustomerString = request.getParameter("genderCustomer");
        String dobCustomerString = request.getParameter("dobCustomer");
        String phoneCustomerString = request.getParameter("phoneCustomer");
        String identityCustomerString = request.getParameter("identityCustomer");
        String addressCustomerString = request.getParameter("addressCustomer");
        String emailCustomerString = request.getParameter("emailCustomer");
        String hireDateString = request.getParameter("hireDate");
        String idRoomString = request.getParameter("idRoom");
        int idRoom = Integer.parseInt(idRoomString);
        Contract contract = contractSql.getContractByIdRoom(idRoom, 1);

        if (nameCustomerString == null || nameCustomerString.matches(" *")) {
            String errorNameCustomer = "Tên khách hàng trống";
            request.setAttribute("errorNameCustomer", errorNameCustomer);
        } else if (genderCustomerString == null) {
            String errorGender = "Giới tính chưa được chọn";
            request.setAttribute("errorGender", errorGender);
        } else if (dobCustomerString == null || dobCustomerString.matches(" *")) {
            String errorDob = "Thông tin ngày chưa được nhập";
            request.setAttribute("errorDob", errorDob);
        } else if (phoneCustomerString == null || phoneCustomerString.matches(" *")) {
            String errorPhone = "Số điện thoại trống";
            request.setAttribute("errorPhone", errorPhone);
        } else if (identityCustomerString == null || identityCustomerString.matches(" *")) {
            String errorIdentity = "Căn cước công dân trống";
            request.setAttribute("errorIdentity", errorIdentity);
        } else if (addressCustomerString == null || addressCustomerString.matches(" *")) {
            String errorAddress = "Địa chỉ trống";
            request.setAttribute("errorAddress", errorAddress);
        } else if (emailCustomerString == null || emailCustomerString.matches(" *")) {
            String errorEmail = "Email trống";
            request.setAttribute("errorEmail", errorEmail);
        } else if (username == null || username.matches(" *")) {
            String errorUsername = "Tên tài khoản trống";
            request.setAttribute("errorUsername", errorUsername);
        } else if (password == null || password.matches(" *")) {
            String errorPassword = "Mật khẩu trống";
            request.setAttribute("errorPassword", errorPassword);
        } else if (accountSql.getAccount(username, password) != null) {
            String errorAccount = "Tên tài khoản và mật khẩu đã tồn tại";
            request.setAttribute("errorAccount", errorAccount);
        } else if (hireDateString == null || hireDateString.matches(" *")) {
            String errorHireDate = "Thông tin ngày chưa được nhập";
            request.setAttribute("errorHireDate", errorHireDate);
        } else if (Date.valueOf(hireDateString).before(contract.getHireDate())) {
            String errorDate = "Thời gian trước ngày tạo hợp đồng";
            request.setAttribute("errorDate", errorDate);
        } else {
            boolean gender = genderCustomerString.equals("1");
            Date dob = Date.valueOf(dobCustomerString);
            Date hireDate = Date.valueOf(hireDateString);

            //insert thong tin
            accountSql.insertAccount(username, password);
            int accountId = accountSql.getAccountInLast();

            Customer cs = new Customer();
            cs.setName(nameCustomerString);
            cs.setGender(gender);
            cs.setDob(dob);
            cs.setPhone(phoneCustomerString);
            cs.setIdentity(identityCustomerString);
            cs.setAddress(addressCustomerString);
            cs.setEmail(emailCustomerString);
            cs.setHireDate(hireDate);
            Account account = new Account();
            account.setId(accountId);
            account.setUsername(username);
            account.setPassword(password);
            cs.setAccount(account);
            Room room = new Room();
            room.setId(idRoom);
            cs.setRoom(room);

            CustomerDBContext customerSql = new CustomerDBContext();
            customerSql.insertCustomer(cs);
            flag = true;
        }
        if (flag) {
            response.sendRedirect("../room/information?id=" + idRoomString + "&status=1");
        } else {
            RoomDBContext roomSql = new RoomDBContext();
            Room room = roomSql.getRoomById(idRoom);

            request.setAttribute("room", room);
            request.getRequestDispatcher("../../view/addCustomer.jsp").forward(request, response);
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
