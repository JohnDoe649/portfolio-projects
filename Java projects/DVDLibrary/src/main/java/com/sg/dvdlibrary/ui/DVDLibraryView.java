package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

public class DVDLibraryView {

    private UserIO io;
    public DVDLibraryView(UserIO io){this.io = io;}

    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a Listing");
        io.print("4. List all DVDs");
        io.print("5. Find DVD by Title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices. ", 1, 6);
    }

    //important view messages tied to the building of the program:
    //Ordered them around the menu listing, with title messages, executables and confirmations.
    //1. Add DVD
    public void viewAddMessage(){
        io.print("=== * ADD DVD * ===");
    }
    public DVD createDVD(){
        String Title = io.readString("Please enter the DVD's title");
        String Release = io.readString("Please enter the DVD's release date");
        String Rating = io.readString("Please enter the DVD's age rating");
        String Director = io.readString("Please enter the director");
        String Studio = io.readString("Please enter the studio");
        String Notes = io.readString("Enter any addition information");
        DVD currentDVD = new DVD(Title);
        currentDVD.setRelease(Release);
        currentDVD.setRating(Rating);
        currentDVD.setDirector(Director);
        currentDVD.setStudio(Studio);
        currentDVD.setNotes(Notes);
        return currentDVD;
    }

    //Prompt to ask the user to select a DVD from the listing
    public String ChosenDVD(){
        return io.readString("Please enter the title of the DVD: ");
    }

    //2. Remove DVD
    public void viewRemoveMessage(){
        io.print("=== * REMOVE DVD * ===");
    }

    //3. Edit DVD
    public void viewEditMessage(){
        io.print("=== * EDIT DVD * ===");
    }
    public DVD editDVD(String Title){
            /*Dev note: Looking at this, I could just have edit DVD in my Controller just perform remove and add
            DVD, but I'd imagine the editing is specifically data tied to the key*/
            String Release = io.readString("Please enter the DVD's release date");
            String Rating = io.readString("Please enter the DVD's age rating");
            String Director = io.readString("Please enter the director");
            String Studio = io.readString("Please enter the studio");
            String Notes = io.readString("Enter any addition information");
            DVD dvd = new DVD(Title);
            dvd.setRelease(Release);
            dvd.setRating(Rating);
            dvd.setDirector(Director);
            dvd.setStudio(Studio);
            dvd.setNotes(Notes);
            return dvd;
    }

    //4. View Library
    public void viewDVDListMessage(){
        io.print("=== * LIST LIBRARY * ===");
    }
    public void displayDVDList(List<DVD> DVDList){
        for(DVD currentDVD : DVDList){
            String DVDInfo = String.format("%s: Released: %s Rated: %s",
                    currentDVD.getTitle(),
                    currentDVD.getRelease(),
                    currentDVD.getRating());
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    //5. View DVD
    public void viewDVDMessage(){
        io.print("=== * LIST DVD * ===");
    }
    public void displayDVD(DVD dvd){
        if(dvd != null){
            io.print("Release date: "+dvd.getRelease());
            io.print("Age Rating: "+dvd.getRating());
            io.print("Director: "+dvd.getDirector());
            io.print("Studio: "+dvd.getStudio());
            io.print("Additional Info: "+dvd.getNotes());
        }
        else {
            io.print("No such title listed.");
        }
        io.readString("Please hit enter to continue.");
    }

    //Exit and Unknown options
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command...?");
    }
    public void displayExitBanner() {
        io.print("See you again!!!");
    }

    //error
    public void ErrorMessage(String error){
        io.print("=== * ERROR * ===");
        io.print(error);
    }

}
