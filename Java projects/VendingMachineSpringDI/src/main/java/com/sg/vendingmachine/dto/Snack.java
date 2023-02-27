package com.sg.vendingmachine.dto;

public class Snack {

    private String ID;
    private String snack;
    private double price; //double value, we'll be using BigDecimal to resolve in service layer
    private int stock;

    public Snack(String ID){this.ID = ID;}
    public String getID(){return ID;}

    public void setSnack(String snack){this.snack = snack;}
    public String getSnack(){return snack;}

    public void setPrice(double price){this.price = price;}
    public double getPrice(){return price;}

    public void setStock(int stock){this.stock = stock;}
    public int getStock(){return stock;}

}
