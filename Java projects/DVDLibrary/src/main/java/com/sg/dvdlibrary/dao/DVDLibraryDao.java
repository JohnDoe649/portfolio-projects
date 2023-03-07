package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;

import java.util.List;

public interface DVDLibraryDao {

    DVD addDVD(String DVDtitle, DVD dvd) throws DVDLibraryDaoException;

    DVD removeDVD(String DVDtitle) throws DVDLibraryDaoException;

    DVD editDVD(String DVDtitle) throws DVDLibraryDaoException;

    List<DVD> getAllDVDs() throws DVDLibraryDaoException;

    DVD getDVD(String DVDtitle) throws DVDLibraryDaoException;

}
