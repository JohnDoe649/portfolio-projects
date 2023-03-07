package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;

import java.util.List;

public class DVDLibraryController {

    private UserIO io = new UserIOConsoleImpl();
    private DVDLibraryDao dao;
    private DVDLibraryView view;

    //dependency injection practice
    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view){
        this.dao = dao;
        this.view = view;
    }

    //menu
    public void run(){
        boolean keepGoing = true;
        int menuSelection;
        try{
            while(keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        newDVD();
                        break;
                    case 2:
                        removeDVD();
                        break;
                    case 3:
                        editDVD();
                        break;
                    case 4:
                        listLibrary();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }catch (DVDLibraryDaoException e) {
            view.ErrorMessage(e.getMessage());
        }
    }


    private int getMenuSelection(){return view.printMenuAndGetSelection();}
    private void newDVD() throws DVDLibraryDaoException{
        view.viewAddMessage();
        DVD newDVD = view.createDVD();
        dao.addDVD(newDVD.getTitle(), newDVD);
    }

    private void removeDVD() throws DVDLibraryDaoException{
        view.viewRemoveMessage();
        String Title = view.ChosenDVD();
        DVD removedDVD = dao.removeDVD(Title);
    }

    private void editDVD() throws DVDLibraryDaoException{
        view.viewEditMessage();
        String Title = view.ChosenDVD();
        dao.removeDVD(Title);
        DVD dvd = view.editDVD(Title);
        dao.addDVD(Title, dvd);
    }

    private void listLibrary() throws DVDLibraryDaoException{
        view.viewDVDListMessage();
        List<DVD> dvdList = dao.getAllDVDs();
        view.displayDVDList(dvdList);
    }

    private void viewDVD() throws DVDLibraryDaoException{
        view.viewDVDMessage();
        String Title = view.ChosenDVD();
        DVD dvd = dao.getDVD(Title);
        view.displayDVD(dvd);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    private void exitMessage() {
        view.displayExitBanner();
    }

}
