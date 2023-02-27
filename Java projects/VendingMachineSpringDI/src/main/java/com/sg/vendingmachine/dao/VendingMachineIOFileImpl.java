package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Snack;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Component
public class VendingMachineIOFileImpl implements VendingMachineIO{

    public static final String VENDING_STOCK = "stock.txt";
    public static final String DELIMITER = "::";
    private boolean loaded = false; //boolean value is used so that we're only loading the list once. We want the stock to be finite.

    @Override
    public List<Snack> displayStock() throws VendingMachineException {
        if(!loaded) {
            loadStock();
            loaded = true;
        }
        return new ArrayList<>(snacks.values());
    }

    @Override
    public void updateStock(String id) {
        //setting a new snack to replace the snack value for a particular key in a map
        Snack replace = snacks.get(id);
        replace.setStock(replace.getStock()-1); //stock has lost 1 via successful purchase
        snacks.replace(id, replace); //replace method lets us change the value of a key in a map
    }

    /**
     * Learned about TreeMap! This should let me sort out my menu items by keys,
     * which is perfect as I want to organize my list alphabetized by keys.
     */
    private Map<String,Snack> snacks = new TreeMap<>();

    private void loadStock() throws VendingMachineException{
        Scanner scan;

        try{
            scan = new Scanner(
                    new BufferedReader(new FileReader(VENDING_STOCK)));
        } catch (FileNotFoundException e){
            throw new VendingMachineException(
                    "Could not load stock into memory.", e
            );
        }

        String currentLine;
        Snack currentSnack;

        while(scan.hasNextLine()){

            currentLine = scan.nextLine();
            currentSnack = unmarshalling(currentLine);

            snacks.put(currentSnack.getID(), currentSnack);

        }

        scan.close();

    }

    /**
     * We only need to unmarshall our file ONCE since we want to work with a finite supply
     * which restocks after closing the application.
     */
    private Snack unmarshalling(String snackAsString){

        String[] snackTokens = snackAsString.split(DELIMITER);
        String snackID = snackTokens[0];
        Snack snackFile = new Snack(snackID);

        snackFile.setSnack(snackTokens[1]);
        snackFile.setPrice(Double.parseDouble(snackTokens[2]));
        snackFile.setStock(Integer.parseInt(snackTokens[3]));

        return snackFile;
    }

}
