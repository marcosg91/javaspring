package com.info.librosprimeraapp.service.csv.book.impl;

import com.info.librosprimeraapp.model.BookCSVRecord;
import com.info.librosprimeraapp.service.csv.book.BookCSVService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Slf4j
@Service
public class BookCSVServiceImpl implements BookCSVService {
    @Override
    public List<BookCSVRecord> convertCSV(File file) throws FileNotFoundException {
        log.info("convirtiendo csv a lista de libros");
        List<BookCSVRecord> bookCSVRecordList =
                new CsvToBeanBuilder<BookCSVRecord>(new FileReader(file))
                        .withType(BookCSVRecord.class)
                        .build()
                        .parse();
        return bookCSVRecordList;
    }
}
