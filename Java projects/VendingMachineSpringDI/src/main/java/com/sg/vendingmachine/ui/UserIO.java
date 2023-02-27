package com.sg.vendingmachine.ui;

public interface UserIO {

    void print(String msg);

    String prompt(String msg);

    int readInt(String msg);
    double readDouble(String msg);
    int readInt(String msg, int min, int max);

}
