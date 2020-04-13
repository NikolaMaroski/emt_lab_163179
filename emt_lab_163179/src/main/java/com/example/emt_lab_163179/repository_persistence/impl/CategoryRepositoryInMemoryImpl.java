package com.example.emt_lab_163179.repository_persistence.impl;

import com.example.emt_lab_163179.model.Category;
import com.example.emt_lab_163179.repository_persistence.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryInMemoryImpl implements CategoryRepository {
    private HashMap<Long, Category> categories;
    private Long counter;

    public CategoryRepositoryInMemoryImpl(){
        this.categories = new HashMap<>();
        this.counter = 0L;
        Long id = this.generateKey();
        this.categories.put(id, new Category(id, "Fantasy", "Fantasy epic story"));
        id = this.generateKey();
        this.categories.put(id, new Category(id, "Comedy", "Comedy all around the world"));
    }

    private Long generateKey() {
        return ++counter;
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(this.categories.values());
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(this.categories.get(id));
    }

    @Override
    public List<Category> findByName(String name) {
        return this.categories.values()
                .stream()
                .filter(item -> item.getName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public Category save(Category category) {
        if(category.getId() == null){
            category.setId(this.generateKey());
        }
        this.categories.put(category.getId(), category);
        return category;
    }

    @Override
    public void deleteById(Long id) {
        this.categories.remove(id);
    }
}
