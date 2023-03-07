package com.sg.dvdlibrary.ui;

public interface UserIO {

    void print(String msg);
    String readString(String prompt);

    int readInt(String msg);
    int readInt(String msg, int min, int max);
}
