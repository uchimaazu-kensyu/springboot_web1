package com.example.lesson.Service;

import com.example.lesson.ProductRecord;

import java.util.List;

public interface ProductService {
    List<ProductRecord> findAll();
    ProductRecord findById(int a);
    int insert(ProductRecord input);
    int update(ProductRecord update);
    int delete(int id);
    int findMaxId();
}
