package com.example.emt_lab_163179.service_business;

import com.example.emt_lab_163179.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findAllByCategoryId(Long categoryId);
    List<Book> findAllSortedByNumberOfSamples(boolean asc);
    Book findById(Long id);
    Book saveBook(Book book, MultipartFile image) throws IOException;
    Book updateBook(Long id, Book book, MultipartFile image) throws IOException;
    void deleteById(Long id);
}
