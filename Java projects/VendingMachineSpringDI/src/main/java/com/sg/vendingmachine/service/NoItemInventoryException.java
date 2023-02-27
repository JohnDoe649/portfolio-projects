package com.sg.vendingmachine.service;

public class NoItemInventoryException extends Exception{

    //error given if the user tries to buy an item out of stock
    public NoItemInventoryException(String message){
        super(message);
    }

    public NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }

}
