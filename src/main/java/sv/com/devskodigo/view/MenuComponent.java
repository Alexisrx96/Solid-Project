package sv.com.devskodigo.view;

import lombok.Getter;
import lombok.Setter;

public abstract class MenuComponent {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String url;

    public MenuComponent(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public abstract void displayMenu();
}