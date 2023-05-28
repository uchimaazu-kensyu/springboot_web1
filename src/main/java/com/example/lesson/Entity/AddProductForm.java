package com.example.lesson.Entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class AddProductForm {

    private Integer productId;


    @NotEmpty
    private String productName;

    @NotNull
    private Integer productPrice;

}
