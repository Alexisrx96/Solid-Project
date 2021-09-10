package sv.com.devskodigo.view;


import java.util.ArrayList;
import java.util.List;

public class Menu extends MenuComponent {

    List<MenuComponent> subMenus = new ArrayList<>();

    public Menu(String name, String url) {
        super(name, url);
    }

    @Override
    public void add(MenuComponent menuComponent) {
        this.subMenus.add(menuComponent);
    }

    @Override
    public void displayMenu() {
        System.out.println(getName() + " : " + getUrl() + "\n");
        this.subMenus.forEach(MenuComponent::displayMenu);
    }
}