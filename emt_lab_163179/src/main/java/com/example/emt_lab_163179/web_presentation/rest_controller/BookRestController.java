package com.example.emt_lab_163179.web_presentation.rest_controller;

import com.example.emt_lab_163179.model.Book;
import com.example.emt_lab_163179.service_business.BookService;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable Long id){
        return this.bookService.findById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Book> findByCategoryId(@PathVariable Long categoryId){
        return this.bookService.findAllByCategoryId(categoryId);
    }

    @GetMapping("/by-price")
    public List<Book> findAllSortedByNumberOfSamples(@RequestParam (required = false, defaultValue = "true") Boolean asc){
        return this.bookService.findAllSortedByNumberOfSamples(asc);
    }

    @PostMapping
    public Book saveBook(@Valid Book book, @RequestParam (required = false)MultipartFile image)throws IOException{
        return this.bookService.saveBook(book, image);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @Valid Book book, @RequestParam (required = false) MultipartFile image)throws IOException{
        return this.bookService.updateBook(id,book, image);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.bookService.deleteById(id);
    }
}
