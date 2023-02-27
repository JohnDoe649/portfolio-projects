package com.sg.vendingmachine.service;

public class InsufficientFundsException extends Exception{

    //Error given if given funds are not greater than or equal to the price of the snack.
    public InsufficientFundsException(String message){
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause){
        super(message, cause);
    }

}
