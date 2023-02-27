package com.sg.vendingmachine.dao;

public class VendingMachineException extends Exception{

    //the general vending machine dao exception (user inputs an incorrect value)
    public VendingMachineException(String message){
        super(message);
    }

    public VendingMachineException(String message, Throwable cause){
        super(message, cause);
    }

}
