package com.sg.dvdlibrary;

import com.sg.dvdlibrary.controller.DVDLibraryController;
import com.sg.dvdlibrary.dao.DVDLibraryDAOFileImpl;
import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args){
        //dependency injection
        UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryDao myDao = new DVDLibraryDAOFileImpl();
        DVDLibraryView myView = new DVDLibraryView(myIO);
        DVDLibraryController control = new DVDLibraryController(myDao, myView);
        control.run();
    }

}
