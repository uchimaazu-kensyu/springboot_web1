package com.example.lesson.Service;

import com.example.lesson.Dao.ProductDao;
import com.example.lesson.Exception.ProductNotFoundException;
import com.example.lesson.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PgProductService implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductRecord> findAll(){
        return productDao.findAll();
    }

    @Override
    public ProductRecord findById(int id){
        var result =  productDao.findById(id);
        if (result == null){
            throw new ProductNotFoundException();
        }else{
            return result;
        }
    }

    @Override
    public int insert(ProductRecord input){
        return productDao.insert(input);
    }

    @Override
    public int update(ProductRecord update){
        return productDao.update(update);
    }

    @Override
    public int delete(int id){
        return productDao.delete(id);
    }

}
