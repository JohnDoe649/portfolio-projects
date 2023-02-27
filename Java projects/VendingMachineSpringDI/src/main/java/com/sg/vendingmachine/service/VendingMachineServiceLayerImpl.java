package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Snack;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private BigDecimal money = new BigDecimal("0.00"); //rounded to two for money
    private enum cents{
        PENNY (0.01),
        NICKEL (0.05),
        DIME (0.10),
        QUARTER (0.25);

        private double value;
        cents(double value) {
            this.value = value;
        }
    }

    @Override
    public void insertingMoney(double amount) {
        //converting the double to a BigDecimal for rounding to proper monetary values.
        BigDecimal convert = BigDecimal.valueOf(amount);
        convert = convert.setScale(2, RoundingMode.CEILING);
        //if-else statement to tell the user if funds have been added successfully.
        if(amount > 0){
            money = money.add(convert);
            money = money.setScale(2, RoundingMode.CEILING);
            System.out.println("Success! $"+convert+" has been added.");
        }
        else System.out.println("No additional funds added.\n");
    }

    @Override
    public BigDecimal showMoney() {
        return money;
    }

    @Override
    public boolean buySnack(List<Snack> snacks, String choice) throws InsufficientFundsException, NoItemInventoryException {

        //setting variables in advance for logic
        Snack select = null;
        for(Snack currentSnack : snacks){
            if(currentSnack.getID().equals(choice)){
                select = currentSnack;
                break;
            }
        }
        int stock = select.getStock();
        BigDecimal calc = money.subtract(BigDecimal.valueOf(select.getPrice()));

        //if statements to throw exceptions should any error in code suffice
        if (stock > 0) {
            if (calc.compareTo(BigDecimal.ZERO) >= 0) {
                System.out.println("Thank you for your purchase! Enjoy your snack!");
                if (calc.compareTo(BigDecimal.ZERO) > 0) {
                    giveChange(calc); //only give change if we have any to give!
                    money = BigDecimal.valueOf(0);
                    return true;
                }
            } else throw new InsufficientFundsException("Insufficient funds! (You have: $" + money.toString() + ")");
        }
        else throw new NoItemInventoryException("That item is out of stock, please select another item.");

        return false;
    }

    //not referencing these methods outside the class, hence only defining it here:
    public void giveChange(BigDecimal remaining) {
        /**
         * bigdecimal functions are immutable, I have to make various ones to perform algorithms
         * definitely a method that I should look into improving/refactoring to avoid repeating
         */
        BigDecimal sub1, sub2, sub3, quarters, dimes, nickles, pennies;

        System.out.println("Here is your change: \n");
        /**
         * General logic is solving for how many coins are given from the change for each coin by division
         * IF the output exists (greater than 0) then it is printed out from the system
         * 3 sub bigdecimals are created to store the difference between the remainder and the output for each coin bar pennies
         */
        //Quarter logic
        quarters = remaining.divide(BigDecimal.valueOf(cents.QUARTER.value), RoundingMode.FLOOR).setScale(0, RoundingMode.DOWN);
        if(quarters.compareTo(BigDecimal.ZERO) > 0) System.out.println("Quarters: "+quarters);
        sub1 = remaining.subtract(quarters.multiply(BigDecimal.valueOf(cents.QUARTER.value)));

        //Dime logic
        dimes = sub1.divide(BigDecimal.valueOf(cents.DIME.value), RoundingMode.FLOOR).setScale(0, RoundingMode.DOWN);
        if(dimes.compareTo(BigDecimal.ZERO) > 0) System.out.println("Dimes: "+dimes);
        sub2 = sub1.subtract(dimes.multiply(BigDecimal.valueOf(cents.DIME.value)));

        //Nickle logic
        nickles = sub2.divide(BigDecimal.valueOf(cents.NICKEL.value), RoundingMode.FLOOR).setScale(0, RoundingMode.DOWN);
        if(nickles.compareTo(BigDecimal.ZERO) > 0) System.out.println("Nickles: "+nickles);
        sub3 = sub2.subtract(nickles.multiply(BigDecimal.valueOf(cents.NICKEL.value)));

        //Penny logic
        pennies = sub3.divide(BigDecimal.valueOf(cents.PENNY.value), RoundingMode.FLOOR).setScale(0, RoundingMode.DOWN);
        if(pennies.compareTo(BigDecimal.ZERO) > 0) System.out.println("Pennies: "+pennies);
    }

}
