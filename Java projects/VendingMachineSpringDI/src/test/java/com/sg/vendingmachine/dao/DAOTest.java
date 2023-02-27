package com.sg.vendingmachine.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest {

    VendingMachineIO dao;

    @BeforeEach
    void set(){dao = new VendingMachineIOFileImpl();}

    //only thing to check without making test specific statements is if unmarshalling is working properly
    @Test
    void displayStock() throws VendingMachineException {
        //May look back at this one to see if I can do more or have more defined testing
        dao.displayStock();
    }

}