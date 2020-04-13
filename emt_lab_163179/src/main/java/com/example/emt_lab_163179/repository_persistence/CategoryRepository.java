package com.example.emt_lab_163179.repository_persistence;

import com.example.emt_lab_163179.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    List<Category> findByName(String name);
    Category save(Category category);
    void deleteById(Long id);
}
