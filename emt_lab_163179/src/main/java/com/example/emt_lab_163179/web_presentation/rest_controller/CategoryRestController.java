package com.example.emt_lab_163179.web_presentation.rest_controller;

import com.example.emt_lab_163179.model.Category;
import com.example.emt_lab_163179.service_business.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(@RequestParam(required = false) String name) {
        if (name == null) {
            return this.categoryService.findAll();
        }else{
            return this.categoryService.findAllByName(name);
        }
    }

    @GetMapping("/by-name")
    public List<Category> findAllByName(@RequestParam String name){
        return this.categoryService.findAllByName(name);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return this.categoryService.findById(id);
    }

    @PostMapping
    public Category save(@Valid Category category){
        return this.categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable  Long id, @Valid Category category){
        return this.categoryService.update(id,category);
    }

    @PatchMapping("/{id}")
    public Category updateName(@PathVariable Long id, @RequestParam String name){
        return this.categoryService.updateName(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.categoryService.deleteById(id);
    }

}
