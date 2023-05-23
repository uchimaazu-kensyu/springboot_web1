package com.example.lesson.Dao;

import com.example.lesson.ProductRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.List.*;
import java.util.List;

@Repository
public class PgProductDao implements ProductDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcForMaxId;

    @Override
    public List<ProductRecord> findAll(){

        return jdbcTemplate.query("SELECT * FROM products ORDER BY id ",
                new DataClassRowMapper<>(ProductRecord.class));

    }

    @Override
    public ProductRecord findById(int id){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        var list = jdbcTemplate.query("SELECT * FROM products WHERE id = :id",param,new DataClassRowMapper<>(ProductRecord.class));
        return  list.isEmpty() ? null:list.get(0);
    }

    @Override
    public int insert(ProductRecord input){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", input.id());
        param.addValue("name", input.name());
        param.addValue("price", input.price());
        return jdbcTemplate.update("INSERT INTO products VALUES(:id, :name, :price)", param);
    }

    @Override
    public int update(ProductRecord update){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", update.id());
        param.addValue("name", update.name());
        param.addValue("price", update.price());
        return jdbcTemplate.update("UPDATE products SET name=:name, price=:price WHERE id =:id", param);
    }

    @Override
    public int delete(int deleteId){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", deleteId);
        return jdbcTemplate.update("DELETE FROM products WHERE id = :id", param);
    }

    public int findMaxId(){
        String sql="SELECT MAX(id) FROM products";
        Integer maxId =jdbcForMaxId.queryForObject(sql, Integer.class);
        return maxId;
    }

}
