package com.sg.dvdlibrary.dao;

public class DVDLibraryDaoException extends Exception{

    //following the DAO exceptions, need to make sure they are needed for this particular assignment
    public DVDLibraryDaoException(String msg){super(msg);}

    public DVDLibraryDaoException(String msg, Throwable cause){super(msg, cause);}

}
