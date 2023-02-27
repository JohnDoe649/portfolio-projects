package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Snack;

import java.util.List;
import java.util.Map;

public interface VendingMachineIO {

    List<Snack> displayStock() throws VendingMachineException;

    void updateStock(String id);

}
