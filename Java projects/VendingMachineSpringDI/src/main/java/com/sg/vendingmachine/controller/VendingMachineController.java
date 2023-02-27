package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dao.VendingMachineIO;
import com.sg.vendingmachine.dto.Snack;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VendingMachineController {

    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineIO dao;

    @Autowired
    public VendingMachineController(VendingMachineIO dao, VendingMachineView view){
        this.dao = dao;
        this.view = view;
    }

    public void run(){
        boolean keepGoing = true;
        int option;
        try {
            while(keepGoing){

                option = displayMenu();
                switch(option){
                    case 1:
                        displayBuyingItem();
                        break;
                    case 2:
                        displayAddingMoney();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default: displayUnknown();
                }
            }

            displayExit();

        }catch(VendingMachineException | NoItemInventoryException | InsufficientFundsException e){
            //need to remember we need a method to print our error message, thanks for the catch Calvin!
            view.errorMessage(e.getMessage());
        }
    }

    //menu methods
    private int displayMenu() throws VendingMachineException {
        List<Snack> snackList = dao.displayStock();
        view.displayStock(snackList);
        return view.displayOptions();
    }

    private void displayBuyingItem() throws VendingMachineException, NoItemInventoryException, InsufficientFundsException {
        List<Snack> snackList = dao.displayStock();
        String bought = view.buyItem(snackList);
        if(bought != null) dao.updateStock(bought); //we should only update the stock if an item was bought
        io.prompt("\nPress Enter to continue: ");
    }

    private void displayAddingMoney(){
        view.addMoney();
    }

    private void displayExit(){
        view.exitMessage();
    }

    private void displayUnknown(){
        view.unknownMessage();
    }

}
