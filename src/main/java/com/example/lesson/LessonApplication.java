package com.example.lesson;

import com.example.lesson.Service.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LessonApplication {

	public static void main(String[] args) {
//		SpringApplication.run(LessonApplication.class, args);
		ConfigurableApplicationContext context =SpringApplication.run(LessonApplication.class, args);
		ProductService productService = context.getBean(ProductService.class);

//		//全件取得
		var list = productService.findAll();
		list.stream().forEach(System.out::println);


//		//id検索
//		var user = productService.findById(1); // IDが1のユーザーのみを取得
//		System.out.println(user);


		//insert　サービスでJDBC.updateになるのに気を付ける
//		var newProduct = new ProductRecord(4, "手すり", 1000);
//		productService.insert(newProduct);
//		var list = productService.findAll();
//		list.stream().forEach(System.out::println);

//		//update
//		var updateProduct = new ProductRecord(4, "手すり3", 10000);
//		productService.update(updateProduct);
//		var list = productService.findAll();
//		list.stream().forEach(System.out::println);

//		//delete
//		productService.delete(4);
//		var list = productService.findAll();
//		list.stream().forEach(System.out::println);










	}


}
