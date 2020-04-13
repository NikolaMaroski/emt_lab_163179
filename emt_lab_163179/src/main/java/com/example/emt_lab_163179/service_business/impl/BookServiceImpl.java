package com.example.emt_lab_163179.service_business.impl;

import com.example.emt_lab_163179.model.Book;
import com.example.emt_lab_163179.model.Category;
import com.example.emt_lab_163179.model.exception.BookNotFoundException;
import com.example.emt_lab_163179.repository_persistence.BookRepository;
import com.example.emt_lab_163179.repository_persistence.CategoryRepository;
import com.example.emt_lab_163179.service_business.BookService;
import com.example.emt_lab_163179.service_business.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    public final BookRepository bookRepository;
    public final CategoryService categoryService;


    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByCategoryId(Long categoryId) {
        return this.bookRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Book> findAllSortedByNumberOfSamples(boolean asc) {
        return this.bookRepository.findAllSortedNoS(asc);
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book saveBook(Book book, MultipartFile image) throws IOException {
        Category category = this.categoryService.findById(book.getCategory().getId());
        book.setCategory(category);
        if(image != null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image =String.format("data:%s;base64,%s",
                    image.getContentType(),
                    Base64.getEncoder().encodeToString(bytes));
            book.setImageBase64(base64Image);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book, MultipartFile image) throws IOException {
        Book b1 = this.findById(id);
        Category c = this.categoryService.findById(book.getCategory().getId());
        b1.setCategory(c);
        b1.setName(book.getName());
        b1.setNumberSamples(book.getNumberSamples());
        if(image != null && !image.getName().isEmpty()){
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            b1.setImageBase64(base64Image);
        }
        return this.bookRepository.save(b1);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
