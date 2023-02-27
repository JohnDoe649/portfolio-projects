package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VendingMachineException;
import com.sg.vendingmachine.dto.Snack;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class VendingMachineView {

    private UserIO io;
    private VendingMachineServiceLayer sl;
    @Autowired
    public VendingMachineView(UserIO io, VendingMachineServiceLayer sl){this.io = io; this.sl = sl;}

    public void displayStock(List<Snack> snackStock){

        System.out.println("CURRENT STOCK: \n");

        for(Snack currentSnack : snackStock){
            String stockInfo = String.format("%s: %s \t$%s\t",
                    currentSnack.getID(),
                    currentSnack.getSnack(),
                    currentSnack.getPrice());
            if(currentSnack.getStock() <= 0) io.print(stockInfo+"**OUT OF STOCK**");
            else System.out.println(stockInfo+currentSnack.getStock()+" in stock");
        }

        System.out.println("\nYou currently have $"+sl.showMoney()+" available.\n");
    }

    public int displayOptions(){

        io.print("Please select one of the following options: ");
        io.print("1. Select an item");
        io.print("2. Deposit cash");
        io.print("3. Exit menu");

        return io.readInt("\nChoose from 1-3: ", 1, 3);
    }

    public String buyItem(List<Snack> snacks) throws NoItemInventoryException, InsufficientFundsException, VendingMachineException {

        String item = io.prompt("Please choose which item you would like to purchase: ");
        for(Snack search : snacks){
            if(search.getID().equals(item)){
                boolean bought = sl.buySnack(snacks, item);
                if(bought) return item;
                else return null;
            }
        }
        io.prompt("Cannot find selection, please try again in the menu. (press Enter to continue)");
        return null;
    }

    public void addMoney(){
        double add = io.readDouble("Please insert the amount of money you would like to add: ");
        sl.insertingMoney(add);
        io.prompt("Press enter to continue...");
        System.out.println();
    }

    public void unknownMessage(){io.print("Unknown option, please select a valid option.");}
    public void exitMessage(){
        io.print("\n\n\n*****Enjoy your snacks and have a wonderful day!*****");
    }

    //catch and print our error messages here:
    public void errorMessage(String e){
        System.out.println("\nSomething went wrong when purchasing your item:\n");
        System.out.println(e);
    }

}
