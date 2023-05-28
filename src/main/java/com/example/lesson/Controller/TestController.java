package com.example.lesson.Controller;

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

@Controller
public class TestController implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Autowired
    ProductService productService;

    @Override
    public void customize(ConfigurableWebServerFactory factory){
        factory.addErrorPages((new ErrorPage(HttpStatus.NOT_FOUND,"/notFoundNew")));
    }

    @RequestMapping("/notFoundNew")
    public String notFoundNew(){
        return "404new";
    }

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
    public String addProductForm(@ModelAttribute("addProduct") AddProductForm addProductForm, BindingResult bindingResult){
        return "product-add";
    }


    @PostMapping("/productAdd")
    public String addProduct(@Validated @ModelAttribute("addProduct") AddProductForm addProductForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  "/product-add";
        }
        int id = productService.findMaxId()+1;
        var addProduct = new ProductRecord (id,addProductForm.getProductName(),addProductForm.getProductPrice());
        productService.insert(addProduct);
        return "redirect:/product-List";
    }



    @GetMapping("/product/update/{id}")
    public String getUpdate(@PathVariable("id") int id,@ModelAttribute("updateProduct") AddProductForm addProductForm , BindingResult bindingResult,Model model) {
        addProductForm = new AddProductForm();
        var product = productService.findById(id);
        addProductForm.setProductId(id);
        addProductForm.setProductName(product.name());
        addProductForm.setProductPrice(product.price());

        model.addAttribute("product", product);
        model.addAttribute("updateProduct", addProductForm);

        return "product-update";
    }

    @PostMapping("/product/update/{id}")
    public String postProduct(@Validated @ModelAttribute("updateProduct") AddProductForm addProductForm , BindingResult bindingResult, @PathVariable("id") int id,Model model){
        if(bindingResult.hasErrors()){
            var product = productService.findById(id);
            model.addAttribute("product", product);
            System.out.println(product);
            model.addAttribute("updateProduct", addProductForm);

            return  "product-update";
        }


//        model.addAttribute("updateProduct", new AddProductForm());
        var updateInfo = new ProductRecord(id,addProductForm.getProductName(),addProductForm.getProductPrice());
        productService.update(updateInfo);
        return "redirect:/product-List";
    }



    @PostMapping("/product/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.delete(id);
        return "redirect:/product-List";
    }


    @GetMapping("/product-management")
    public String productList2(){
        return "product-list3";
    }



    @GetMapping("/user")
    public String index2() {
        return "user";
    }

}