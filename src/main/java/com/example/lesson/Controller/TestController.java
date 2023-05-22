package com.example.lesson.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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
}