package com.sg.dvdlibrary.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{

    Scanner scan = new Scanner(System.in);
    Boolean loop; int inbetween; String post;

    /*We have string values to work with and integers for menu selection
    Hence I only have methods to check those two values.*/
    public void print(String message){
        System.out.println(message);
    }

    public String readString(String prompt){
        System.out.println(prompt);
        post = scan.nextLine();
        //System.out.println("You have typed out: " + post); (not necessary for this assignment)
        return post;
    }

    public int readInt(String msg){
        System.out.println(msg);
        return Integer.parseInt(scan.nextLine());
    }

    public int readInt(String msg, int min, int max) {
        loop = true;

        if(min > max){
            int temp = max;
            max = min;
            min = temp;
        }

        do {
            System.out.println(msg + min + " : " + max);
            inbetween = Integer.parseInt(scan.nextLine());
            if(inbetween < min || inbetween > max) System.out.println("Error! Please select a valid option!\n");
            else loop = false;
        } while (loop);

        return inbetween;
    }

}
