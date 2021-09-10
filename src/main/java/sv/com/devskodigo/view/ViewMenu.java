package sv.com.devskodigo.view;

import sv.com.devskodigo.services.MenuItemAction;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewMenu {
    private int modelOption = 0;
    private int crudAction = 0;
    private int userContinue = 1;

    private final int MENU_MAX_VALUE = 8; //equivalent to max menu options

    MenuComponent catalogs;
    MenuComponent flights;
    MenuComponent exit;

    public void createMenu(){
        catalogs = new Menu("Catalogs", "");
        flights = new Menu("Flights Operations", "");
        exit = new Menu("Active Session", "");

        catalogs.add(new MenuItem("Users", "1"));
        catalogs.add(new MenuItem("Country", "2"));
        catalogs.add(new MenuItem("City   ", "3"));
        catalogs.add(new MenuItem("Airline", "4"));
        catalogs.add(new MenuItem("Aircraft", "5"));

        flights.add(new MenuItem("Flights", "6"));
        flights.add(new MenuItem("Reports", "7"));

        exit.add(new MenuItem("Exit   ", "8"));
    }

    public void displayMenu(){
        catalogs.displayMenu();
        System.out.println("--------------------");
        System.out.println();

        flights.displayMenu();
        System.out.println("--------------------");
        System.out.println();

        exit.displayMenu();
    }

    public void displayCrudMenu(){
        System.out.println("================");
        System.out.println("Insert a Record -> 1");
        System.out.println("Display Records -> 2");
        System.out.println("Delete a Record -> 3");
        System.out.println("Update a Record -> 4");
        System.out.println("================");
    }

    public void runMenu(){
        do{
            this.displayMenu();

            try{
                Scanner rawData = new Scanner(System.in);

                System.out.println("Please type your option:");
                modelOption = rawData.nextInt();
                if(modelOption < 1 || modelOption > MENU_MAX_VALUE) {
                    System.out.println("Invalid option, please try again");
                }else if(modelOption == 8){
                    System.exit(0);
                }
                else {
                    this.displayCrudMenu();
                    System.out.println("Please type of the above data action:");
                    crudAction = rawData.nextInt();
                    if(crudAction < 1 || crudAction > 4) {
                        System.out.println("Invalid option, please try again");
                    }
                    else{
                        MenuItemAction menuItemAction = new MenuItemAction();
                        menuItemAction.executeAction(modelOption, crudAction);
                    }
                }


                System.out.println("Would you like to continue? 1->Yes, 0->No: ");
                userContinue = rawData.nextInt();
                if (userContinue == 0){
                    rawData.close();
                }
            }
            catch(InputMismatchException ime){
                System.out.println("Please type only integer numbers greater than zero");
            }
        }while(userContinue == 1);
    }

}
