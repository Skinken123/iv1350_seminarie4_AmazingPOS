package se.kth.iv1350.amazingpos.main.startup;

import se.kth.iv1350.amazingpos.main.view.View;

import java.io.IOException;

import se.kth.iv1350.amazingpos.main.controller.Controller;
import se.kth.iv1350.amazingpos.main.integration.ExternalSystemsCreator;
import se.kth.iv1350.amazingpos.main.integration.ReceiptPrinter;

/**
 * Contains the <code>main</code> method. Performs all startup of the application.
 */

public class Main { 
    /**
     * Starts the application.
     * 
     * @param args The application does not take any command line parameters.
     */
    public static void main(String[] args) {
        try{
            ExternalSystemsCreator creator = new ExternalSystemsCreator();
            ReceiptPrinter printer = new ReceiptPrinter();
            Controller contr = new Controller(creator, printer);
            View view = new View(contr);
            view.sampleExecution();
        }
        catch(IOException exc){
            System.out.println("An error has occured. Please try starting the application again.");
        }
    }
}
