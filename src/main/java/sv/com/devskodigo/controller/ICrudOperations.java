package sv.com.devskodigo.controller;

public interface ICrudOperations {
    void crudPipeline();
    void getData();
    void saveData();
    void viewData();
    int searchData();
    void deleteData(int i);
    void updateData(int i);


}
