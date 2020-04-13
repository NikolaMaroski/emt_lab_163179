package com.example.emt_lab_163179.repository_persistence;

import com.example.emt_lab_163179.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();
    List<Book> findAllSortedNoS(boolean asc);
    List<Book> findByCategoryId(Long categoryId);
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}
