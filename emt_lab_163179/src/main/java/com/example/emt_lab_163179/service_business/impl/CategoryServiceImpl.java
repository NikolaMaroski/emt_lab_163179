package com.example.emt_lab_163179.service_business.impl;

import com.example.emt_lab_163179.model.Category;
import com.example.emt_lab_163179.model.exception.CategoryNotFoundException;
import com.example.emt_lab_163179.repository_persistence.CategoryRepository;
import com.example.emt_lab_163179.service_business.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<Category> findAllByName(String name) {
        return this.categoryRepository.findByName(name);
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category c = this.findById(id);
        c.setName(category.getName());
        c.setDescription(category.getDescription());
        return this.categoryRepository.save(c);
    }

    @Override
    public Category updateName(Long id, String name) {
        Category c = this.findById(id);
        c.setName(name);
        return this.categoryRepository.save(c);
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }
}
