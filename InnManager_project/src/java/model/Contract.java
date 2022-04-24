/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author firem
 */
public class Contract {
    private int id;
    private double deposit;
    private double priceConduct;
    private Date hireDate;
    private Date checkOutDate;
    private boolean status;
    private Customer customer;
    private Room room;
    private ArrayList<Conduct> listConduct;
    private ContractDetail contractDetail;

    public Contract() {
        customer = new Customer();
        room = new Room();
        listConduct = new ArrayList<>();
        contractDetail = new ContractDetail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getPriceConduct() {
        return priceConduct;
    }

    public void setPriceConduct(double priceConduct) {
        this.priceConduct = priceConduct;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ArrayList<Conduct> getListConduct() {
        return listConduct;
    }

    public void setListConduct(ArrayList<Conduct> listConduct) {
        this.listConduct = listConduct;
    }

    public ContractDetail getContractDetail() {
        return contractDetail;
    }

    public void setContractDetail(ContractDetail contractDetail) {
        this.contractDetail = contractDetail;
    }
    
}
