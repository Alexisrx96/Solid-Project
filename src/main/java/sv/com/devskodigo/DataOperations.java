package sv.com.devskodigo;

/*
name: DataOperations.java -> Interface
purpose: pattern for managing of persistence operations
author: hftamayo
comments:
1. testing

 */

import sv.com.devskodigo.dto.ReportDetailDto;

import java.util.List;

public interface DataOperations<T> {
    void readDataset();
    void addData(T t);
    void updateData(T t);
    List<T> dataList();
    void searchData(int id);
    void deleteData(int id);
}
