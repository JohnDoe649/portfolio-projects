package com.sg.vendingmachine.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserIOConsoleImpl implements UserIO{

    Scanner scan = new Scanner(System.in);
    Boolean loop; int inbetween; String response;

    public void print(String msg){
        System.out.println(msg);
    }

    public String prompt(String msg){
        System.out.println(msg);
        response = scan.nextLine();
        return response;
    }

    public int readInt(String msg){
        System.out.println(msg);
        return Integer.parseInt(scan.nextLine());
    }

    public double readDouble(String msg){
        System.out.println(msg);
        return Double.parseDouble(scan.nextLine());
    }

    public int readInt(String msg, int min, int max) {
        loop = true;

        if(min > max){
            int temp = max;
            max = min;
            min = temp;
        }

        do {
            print(msg);
            inbetween = Integer.parseInt(scan.nextLine());
            if(inbetween < min || inbetween > max) System.out.println("Error! Please select a valid option!\n");
            else loop = false;
        } while (loop);

        return inbetween;
    }

}
