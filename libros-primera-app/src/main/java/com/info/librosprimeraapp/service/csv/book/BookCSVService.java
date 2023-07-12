package com.info.librosprimeraapp.service.csv.book;

import com.info.librosprimeraapp.model.BookCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
//se encarga de la conversion de datos csv
public interface BookCSVService {
    List<BookCSVRecord> convertCSV(File file) throws FileNotFoundException;
}
