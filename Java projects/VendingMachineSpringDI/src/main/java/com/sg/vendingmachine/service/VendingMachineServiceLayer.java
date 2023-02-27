package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dto.Snack;

import java.math.BigDecimal;
import java.util.List;

public interface VendingMachineServiceLayer {

    void insertingMoney(double amount);

    BigDecimal showMoney();

    boolean buySnack(List<Snack> snacks, String choice) throws InsufficientFundsException, NoItemInventoryException, VendingMachineException;


}
