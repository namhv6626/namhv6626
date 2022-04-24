/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.base.BaseController;
import dal.AccountDBContext;
import dal.ConductDBContext;
import dal.ContractDBContext;
import dal.ContractDetailDBContext;
import dal.CustomerDBContext;
import dal.PaymentDBContext;
import dal.RoomDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Conduct;
import model.Contract;
import model.Customer;
import model.Payment;
import model.Room;
import validator.InputValidation;

/**
 *
 * @author firem
 */
public class AddContractController extends BaseController {

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
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);

        //lay thong tin tu database
        RoomDBContext roomSql = new RoomDBContext();
        Room room = roomSql.getRoomById(id);
        ConductDBContext conductSql = new ConductDBContext();
        ArrayList<Conduct> listConduct = conductSql.getListConduct();

        request.setAttribute("room", room);
        request.setAttribute("listConduct", listConduct);
        request.getRequestDispatcher("../../view/addContract.jsp").forward(request, response);
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
        RoomDBContext roomSql = new RoomDBContext();
        ConductDBContext conductSql = new ConductDBContext();
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

        String idRoomString = request.getParameter("idRoom");
        String depositString = request.getParameter("deposit");
        String hireDateString = request.getParameter("hireDate");
        String roomNameString = request.getParameter("roomName");
        int idRoom = Integer.parseInt(idRoomString);

        if (!InputValidation.isDouble(depositString)) {
            String errorDeposit = "Giá trị không phải là số";
            request.setAttribute("errorDeposit", errorDeposit);
        } else if (hireDateString == null || hireDateString.matches(" *")) {
            String errorHireDate = "Thông tin ngày chưa được nhập";
            request.setAttribute("errorHireDate", errorHireDate);
        } else if (nameCustomerString == null || nameCustomerString.matches(" *")) {
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
        } else {
            boolean gender = genderCustomerString.equals("1");
            Date dob = Date.valueOf(dobCustomerString);
            double deposit = Double.parseDouble(depositString);
            Date hireDate = Date.valueOf(hireDateString);

            //insert thong tin
            //add information for account
            accountSql.insertAccount(username, password);
            int accountId = accountSql.getAccountInLast();
            //add information for customer
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
            int customerId = customerSql.getCustomerInLast();
            cs.setId(customerId);
            //add information for contract
            Contract contract = new Contract();
            contract.setCustomer(cs);
            contract.setRoom(room);
            contract.setDeposit(deposit);
            contract.setHireDate(hireDate);
            ContractDBContext contractSql = new ContractDBContext();
            contractSql.insertContract(contract);
            int contractId = contractSql.getContractInLast();
            contract.setId(contractId);
            //add information for conductService
            
            ArrayList<Conduct> listConduct = conductSql.getListConduct();
            for (Conduct cd : listConduct) {
                String numberString = request.getParameter("conduct" + cd.getId());
                int number = Integer.parseInt(numberString);
                for (int i = 0; i < number; i++) {
                    conductSql.insertConductService(cd.getId(), contractId);
                }
            }
            //add information for payment
            Payment pm = new Payment();
            pm.setContract(contract);
            pm.setFromDate(hireDate);
            Date toDate = Date.valueOf(hireDate.toLocalDate().plusMonths(1));
            pm.setToDate(toDate);
            PaymentDBContext paymentSql = new PaymentDBContext();
            paymentSql.insertPayment(pm);
            //update status room
            roomSql.updateRoomForActive(idRoom);
            //add contractDetail
            Room roomById = roomSql.getRoomById(idRoom);
            ContractDetailDBContext contractDetailSql = new ContractDetailDBContext();
            contractDetailSql.insertContractDetail(contractId, roomById.getRoomType().getPrice());
            
            flag = true;
        }
        if(flag){
            response.sendRedirect("../room/list?search=" + roomNameString);
        }else{
            Room room = roomSql.getRoomById(idRoom);
            ArrayList<Conduct> listConduct = conductSql.getListConduct();

            request.setAttribute("room", room);
            request.setAttribute("listConduct", listConduct);
            request.getRequestDispatcher("../../view/addContract.jsp").forward(request, response);
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
