package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.entity.Category;

import java.util.List;

public interface CategoryRepository {
    Category get(int id);
    List<Category>list();
    boolean add(Category category);
    boolean update(Category category);
    boolean delete(Category category);
}
