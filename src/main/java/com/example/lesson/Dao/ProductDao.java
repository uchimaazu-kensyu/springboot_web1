package com.example.lesson.Dao;

import com.example.lesson.ProductRecord;
import java.util.List;



public interface ProductDao {
    List<ProductRecord> findAll();
    ProductRecord findById(int a);
    int insert(ProductRecord input);
    int update(ProductRecord update);
    int delete(int id);

    int findMaxId();
}
