package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args){
    /*
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineIO myDao = new VendingMachineIOFileImpl();
        VendingMachineServiceLayer mySL = new VendingMachineServiceLayerImpl();
        VendingMachineView myView = new VendingMachineView(myIO, mySL);
        VendingMachineController control = new VendingMachineController(myDao, myView);
    */
        //AnnotationConfigApplicationContext appAC = new AnnotationConfigApplicationContext();
        //appAC.scan("com.sg.vendingmachine");
        //appAC.refresh();

        //Spring DI without Annotations (annotations currently cause error in finding beans)
        ApplicationContext appAC = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        VendingMachineController control = appAC.getBean("control", VendingMachineController.class);
        control.run();
    }

}
