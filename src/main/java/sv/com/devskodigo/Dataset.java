package sv.com.devskodigo;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dataset {

    /*
    open DataSet, if the file is not found, it will be created
    params: fileName
     */
    public void openDataset(String filename){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Country");

    }

    /*
    write the data into the file
    param: List<>
     */
    public void newData(){
        //TODO
    }

    /*
    update existing data and write it into the file
    param: List<>, id
     */
    public void updateData(){
        //TODO
    }

    /*
    delete a record in specific, write changes into the file
    param: id
     */
    public void deleteData(){
        //TODO
    }


}
