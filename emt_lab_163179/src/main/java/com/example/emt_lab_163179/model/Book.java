package com.example.emt_lab_163179.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Book {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Min(value = 1, message = "Value must be bigger than  0")
    private Long numberSamples;
    @NotNull
    private Category category;
    private String imageBase64;

    public Book(Long id, String name, Long numberSamples, Category category) {
        this.id = id;
        this.name = name;
        this.numberSamples = numberSamples;
        this.category = category;
    }
    public Book(){}
}
