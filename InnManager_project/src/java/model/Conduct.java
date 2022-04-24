/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author firem
 */
public class Conduct {
    private int id;
    private String name;
    private double price;
    private ConductType conductType;

    public Conduct() {
        conductType = new ConductType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ConductType getConductType() {
        return conductType;
    }

    public void setConductType(ConductType conductType) {
        this.conductType = conductType;
    }
    
    public long getPriceLong(){
        return (long)price;
    }
}
