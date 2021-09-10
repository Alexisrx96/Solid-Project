package sv.com.devskodigo;
/*
name: FlightsErp
purpose:
-project's main class, instantiate menu and menutitems
-the class MenuItemAction is in charge to handle user input
author: hftamayo
 */


import sv.com.devskodigo.view.ViewMenu;

public class FlightsErp {


    public static void main(String[] args){
        System.out.println("Welcome to the Airport, please choose one of the bellow options:");
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.createMenu();
        viewMenu.runMenu();

    }//end of main


}
