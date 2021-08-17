package sv.com.devskodigo;

/*
name: DataOperations.java -> Interface
purpose: pattern for managing of persistence operations
author: hftamayo
comments:
1. testing

 */

public interface DataOperations {
    void readDataset();
    void addData();
    void updateData();
    void searchData();
    void deleteData();
    void updateStatus();
}
