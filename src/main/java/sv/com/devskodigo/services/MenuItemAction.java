package sv.com.devskodigo.services;

import sv.com.devskodigo.controller.CountryController;

public class MenuItemAction {
    private int modelUserChosen = 0;
    private int crudActionChosen = 0;

    public MenuItemAction(){

    }

    public void executeAction(int mo, int co){
        modelUserChosen = mo;
        crudActionChosen = co;


        switch(modelUserChosen){
            case 1:
                //USER CRUD

                break;
            case 2:
                //COUNTRY CRUD
                CountryController countryController = new CountryController(co);
                break;
            case 3:
                //CITY CRUD
                //ViewCity viewCity = new ViewCity(co);
                break;
            case 4:
                //AIRLINE CRUD
                //ViewAirline viewAirline = new ViewAirline(co);
                break;
            case 5:
                //AIRCRAFT CRUD
                //ViewAircraft viewAircraft = new ViewAircraft(co);
                break;
            case 6:
                //FLIGHTS CRUD
                //ViewFlights viewFlights = new ViewFlights(co);
                break;
            case 7:
                //REPORTS CRUD
                //ViewReports viewReports = new ViewReports(co);
                break;
            case 8:
                //EXIT
                System.out.println("Thank you for use our services!");
                System.exit(0);
                break;
        }

    }//end of executeOption
}
