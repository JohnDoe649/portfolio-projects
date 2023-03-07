package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDAOFileImpl implements DVDLibraryDao{

    public static final String ROSTER_FILE = "library.txt";
    public static final String DELIMITER = "::";


    @Override
    public DVD addDVD(String DVDtitle, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD newDVD = DVDs.put(DVDtitle, dvd);
        writeLibrary();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String DVDtitle) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDVD = DVDs.remove(DVDtitle);
        writeLibrary();
        return removedDVD;
    }

    @Override
    public DVD editDVD(String DVDtitle) throws DVDLibraryDaoException {
        return null;
    }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList(DVDs.values());
    }

    @Override
    public DVD getDVD(String DVDtitle) throws DVDLibraryDaoException {
        loadLibrary();
        return DVDs.get(DVDtitle);
    }

    private Map<String, DVD> DVDs = new HashMap<>();

    private String marshallDVD(DVD aDVD){
        //marshalling: setting our object to a note file that can be loaded
        String dvdAsText = aDVD.getTitle()+DELIMITER;
        dvdAsText += aDVD.getRelease()+DELIMITER;
        dvdAsText += aDVD.getRating()+DELIMITER;
        dvdAsText += aDVD.getDirector()+DELIMITER;
        dvdAsText += aDVD.getStudio()+DELIMITER;
        dvdAsText += aDVD.getNotes()+DELIMITER;
        return dvdAsText;
    }
    private DVD unmarshallDVD(String DVDAsText){

        //unmarshalling
        String[] DVDTokens = DVDAsText.split(DELIMITER);
        String Title = DVDTokens[0];
        DVD DVDFromFile = new DVD(Title);

        //Setting each field from the file
        DVDFromFile.setRelease(DVDTokens[1]);
        DVDFromFile.setRating(DVDTokens[2]);
        DVDFromFile.setDirector(DVDTokens[3]);
        DVDFromFile.setStudio(DVDTokens[4]);
        DVDFromFile.setNotes(DVDTokens[5]);

        return DVDFromFile;
    }
    private void writeLibrary() throws DVDLibraryDaoException{
        PrintWriter export;

        try{
            export = new PrintWriter(new FileWriter(ROSTER_FILE));
        } catch (IOException e){
            throw new DVDLibraryDaoException("Could not save DVD library.", e);
        }

        String dvdAsText;
        List<DVD> dvdList = this.getAllDVDs();
        for(DVD currentDVD : dvdList){
            dvdAsText = marshallDVD(currentDVD);
            export.println(dvdAsText);
            export.flush();
        }
        export.close();
    }

    private void loadLibrary() throws DVDLibraryDaoException{
        Scanner iport;

        try{
            iport = new Scanner(
                        new BufferedReader(new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e){
            throw new DVDLibraryDaoException(
                    "Could not load library into memory.", e
            );
        }
        String currentLine;
        DVD currentDVD;
        while(iport.hasNextLine()){
            currentLine = iport.nextLine();
            currentDVD = unmarshallDVD(currentLine);

            DVDs.put(currentDVD.getTitle(), currentDVD);
        }

        iport.close();
    }

}
