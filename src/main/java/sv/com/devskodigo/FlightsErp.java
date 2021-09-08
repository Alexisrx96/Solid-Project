package sv.com.devskodigo;
/*
name: FlightsErp
purpose: project's main class, it's the pipeline for submenu and execute a requested class
author: hftamayo
comments:
1. clean the screen fuction -> OS independent
2. loop back to main menu
 */

import sv.com.devskodigo.model.dto.ReportSummaryDto;
import sv.com.devskodigo.model.dao.toxlsx.CityToXlsx;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FlightsErp {
    private static Scanner rawData = new Scanner(System.in);

    public static void showSubMenu(int mnu){
        int subMenuOption = 0;
        System.out.println("Please type the number related to each submenu ");
        switch(mnu){
            case 1:
                System.out.println("1. Country");
                System.out.println("2. City");
                System.out.println("3. Airlines");
                System.out.println("4. Aircrafts");
                System.out.println("5. Users");
                subMenuOption = rawData.nextInt();

                switch(subMenuOption){
                    case 1:
                        break;
                    case 2:
                        CityToXlsx city = new CityToXlsx();
                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                }//end of catalog submenu options
                break;

            case 2:
                System.out.println("1. Add a Flight");
                System.out.println("2. Search a Flight");
                System.out.println("3. update Flight's Status");
                subMenuOption = rawData.nextInt();
                switch (subMenuOption){
                    case 4:
                        Flight flight = new Flight();
                        flight.getWeatherRpt();
                        break;
                }//end of flight submenu options
                break;

            case 3:
                System.out.println("1. Add a Report");
                System.out.println("2. Send Report");
                System.out.println("3. update Report's Status");
                subMenuOption = rawData.nextInt();
                switch (subMenuOption){
                    case 1:
                        ReportSummaryDto reportSummary = new ReportSummaryDto();
                        break;
                    case 2:
                        //TODO
                        break;
                } //end of report submenu options
                break;
        }//end of first switch loop
    }//end of showSubMenu method

    public static void main(String[] args){
        int menuOption = 0;

        System.out.println("Welcome to Flights ERP - Comalapa Airport");
        System.out.println("Main Menu: please type the number related to one of the above options: ");
        System.out.println("1. Catalogs");
        System.out.println("2. Flight Operations");
        System.out.println("3. Reports");
        System.out.println("4. Exit");
        try{
            menuOption = rawData.nextInt();
            switch(menuOption){
                case 1:
                case 2:
                case 3:
                    showSubMenu(menuOption);
                    break;
                default:
                    System.out.println("thank you for use our product. Good bye");
                    break;
            }//end of switch
        }catch(InputMismatchException ime){
            System.out.println("Please type only integer numbers");
            ime.printStackTrace();
        }
    }//end of main
}

