package sv.com.devskodigo;

import lombok.Getter;
import lombok.Setter;

public class Aircraft implements DataOperations {

    @Getter
    @Setter
    private static int x;

    @Getter
    @Setter
    private Airline airline;

    public Aircraft(Airline airline) {
        this.airline = airline;
    }
    public Aircraft() {
    }

    @Override
    public void readDataset(){

    }

    @Override
    public void addData(){

    }

    @Override
    public void updateData(){

    }

    @Override
    public void searchData(){

    }

    @Override
    public void deleteData(){

    }

    @Override
    public void updateStatus(){

    }
}
