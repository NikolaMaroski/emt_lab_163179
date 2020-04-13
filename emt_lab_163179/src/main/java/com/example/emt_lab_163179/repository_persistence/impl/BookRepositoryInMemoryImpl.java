package com.example.emt_lab_163179.repository_persistence.impl;

import com.example.emt_lab_163179.model.Book;
import com.example.emt_lab_163179.repository_persistence.BookRepository;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryInMemoryImpl implements BookRepository {
    private HashMap<Long, Book> books;
    private Long counter;

    public BookRepositoryInMemoryImpl() {
        this.books = new HashMap<>();
        this.counter = 0L;
        Long id = this.generateKey();
        this.books.put(id, new Book(id,"b1", 10L, null));
        id = this.generateKey();
        this.books.put(id, new Book(id,"b2", 20L, null));
        id = this.generateKey();
        this.books.put(id, new Book(id,"b3", 40L, null));
        id = this.generateKey();
        this.books.put(id, new Book(id,"b4", 15L, null));
    }

    private Long generateKey() {
        return ++counter;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public List<Book> findAllSortedNoS(boolean asc) {
        Comparator<Book> priceComparator = Comparator.comparing(Book::getNumberSamples);
        if(!asc){
            priceComparator = priceComparator.reversed();
        }
        return this.books.values()
                .stream()
                .sorted(priceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findByCategoryId(Long categoryId) {
        return this.books.values()
                .stream()
                .filter(item -> item.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == null){
            book.setId(this.generateKey());
        }
        this.books.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        this.books.remove(id);
    }
}
