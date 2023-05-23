package com.example.lesson.Controller;

import com.example.lesson.Entity.AddProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.lesson.Service.*;
import com.example.lesson.Dao.*;
import com.example.lesson.*;


import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    ProductService productService;

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "Hello Spring";
    }

    @GetMapping("/product-List")
    public String productList(Model model) {
        var list = productService.findAll();
        model.addAttribute("products", list);
        return "product-list";

    }

    @GetMapping("/product/{id}")
    public String date2(@PathVariable("id") int id,Model model) {
        var product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/productAdd")
    public String addProductForm(@ModelAttribute("addProduct") AddProductForm addProductForm){
        return "product-add";
    }


    @PostMapping("/productAdd")
    public String addProduct(@ModelAttribute("addProduct") AddProductForm addProductForm){
        int id = productService.findMaxId()+1;
        var addProduct = new ProductRecord (id,addProductForm.getProductName(),addProductForm.getProductPrice());
        productService.insert(addProduct);
        return "redirect:/product-List";
    }



    @GetMapping("/product/update/{id}")
    public String getUpdate(@PathVariable("id") int id,Model model) {
        AddProductForm addProductForm = new AddProductForm();
        var product = productService.findById(id);
        addProductForm.setProductName(product.name());
        addProductForm.setProductPrice(product.price());

        model.addAttribute("product", product);
        model.addAttribute("updateProduct", addProductForm);

        return "product-update";
    }

    @PostMapping("/product/update/{id}")
    public String postProduct(@PathVariable("id") int id,@ModelAttribute("updateProduct") AddProductForm addProductForm ,Model model){
        model.addAttribute("updateProduct", new AddProductForm());
        var updateInfo = new ProductRecord(id,addProductForm.getProductName(),addProductForm.getProductPrice());
        productService.update(updateInfo);
        return "redirect:/product-List";
    }

    @PostMapping("/product/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product-List";
    }

}