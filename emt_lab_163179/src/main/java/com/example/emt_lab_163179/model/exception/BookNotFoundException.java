package com.example.emt_lab_163179.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends  RuntimeException{
    public BookNotFoundException(Long id){
        super(String.format("Book with id %d is not found", id));
    }
}
