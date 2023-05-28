package com.example.lesson.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

//TestControllerからコピー
import com.example.lesson.Entity.AddProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.lesson.Service.*;
import com.example.lesson.Dao.*;
import com.example.lesson.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    private ProductService productService;

    private ProductRecord productRecord;
    private AddProductForm addProductForm;
    private record Users(int id, String name, String mail){};


    @GetMapping("/api/products")
    public List<ProductRecord> findAll(){
        try{
            List<ProductRecord> productList =productService.findAll();;
            return productList ;
        }catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/api/users")
    public List<Users> getUsers() {
        return List.of(
                new Users(1, "test-1", "test-1@example.com"),
                new Users(2, "test-2", "test-2@example.com"),
                new Users(3, "test-3", "test-3@example.com"),
                new Users(4, "test-4", "test-4@example.com"),
                new Users(5, "test-5", "test-5@example.com")
        );
    }

    @PutMapping("/api/update")
    public void updateProduct(ProductRecord p){
        System.out.println(p);
        int a =productService.update(p);
        return;
    }

    @GetMapping("/api/products/{id}")
    public ProductRecord selectButton(@PathVariable("id") Integer id){
        var result = productService.findById(id);
        return result;
    }

    @PutMapping("/api/products/")
    public int updateButton(@RequestBody ProductRecord p){
        System.out.println(p);
        var result = productService.update(p);
        return result;
    }


    @DeleteMapping("api/products/{id}")
    public int deleteButton(@PathVariable("id") int id){
        System.out.println(id);
        int result = productService.delete(id);
        return result;
    }

    @GetMapping("/api/getMaxId")
    public int getMaxId(){
        int maxId =productService.findMaxId();
        return maxId;
    }


    @PostMapping("/api/product")
    public int addProduct(@RequestBody ProductRecord p){
        System.out.println(p);
        int a =productService.insert(p);
        return a;
    }







}
