package com.info.librosprimeraapp.bootstrap;

import com.info.librosprimeraapp.domain.Book;
import com.info.librosprimeraapp.model.BookCSVRecord;
import com.info.librosprimeraapp.repository.book.BookRepository;
import com.info.librosprimeraapp.service.csv.book.BookCSVService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j

public class BootstrapData implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final BookCSVService bookCSVService;

    @Override
    public void run(String...args) throws Exception{
        log.info("corriendo BootstrapData");
        loadBookData();
    }

    //carga de cvs
    private void loadBookData() throws FileNotFoundException {
        if (bookRepository.count() < 100){
            File file = ResourceUtils.getFile("classpath:csvdata/book_data.csv");
            List<BookCSVRecord> bookCSVRecordList = bookCSVService.convertCSV(file);

            if (!bookCSVRecordList.isEmpty()){
                log.info("cargando base de datos");
                for (BookCSVRecord bookCSVRecord: bookCSVRecordList){
                    bookRepository.save(
                            Book.builder()
                                    .uuid(UUID.randomUUID())
                                    .isbn(bookCSVRecord.getIsbn())
                                    .title(bookCSVRecord.getTitle())
                                    .author(bookCSVRecord.getAuthor())
                                    .numberPage(Integer.parseInt(bookCSVRecord.getNumberPage()))
                                    .build()
                    );
                }
            }
        }
    }
}
