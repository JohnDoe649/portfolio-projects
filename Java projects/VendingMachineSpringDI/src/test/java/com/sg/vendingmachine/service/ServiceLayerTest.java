package com.sg.vendingmachine.service;

import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ServiceLayerTest {

    VendingMachineServiceLayer sl;

    //We'll be testing money inputs for this assignment
    @BeforeEach
    void set(){
        sl = new VendingMachineServiceLayerImpl();
    }

    @org.junit.jupiter.api.Test
    void testInsertingMoneyValid() {
        //we're testing a void method, so I'll have to do a few work just to get a valid assertion
        //Thankfully I can use the showMoney method to confirm if the method has gone through!
        double money = 2.34;
        sl.insertingMoney(money);
        BigDecimal test = sl.showMoney();
        double result = test.doubleValue();
        assertEquals(2.34, result);
    }

    @org.junit.jupiter.api.Test
    void testInsertingMoneyRounded() {
        //For this test, we want to make sure if the user goes past a scale of 2, that the money will be rounded UP (hence .341 should give back .35)
        double money = 2.341;
        sl.insertingMoney(money);
        BigDecimal test = sl.showMoney();
        double result = test.doubleValue();
        assertEquals(2.35, result);
    }

    @org.junit.jupiter.api.Test
    void testInsertingMoneyZero() {
        double money = 0;
        sl.insertingMoney(money);
        BigDecimal test = sl.showMoney();
        double result = test.doubleValue();
        assertEquals(0, result);
    }

    @org.junit.jupiter.api.Test
    void testInsertingMoneyNegative() {
        //Negative values don't add money, so the value should remain 0!
        double money = -3.68;
        sl.insertingMoney(money);
        BigDecimal test = sl.showMoney();
        double result = test.doubleValue();
        assertEquals(0, result);
    }

}