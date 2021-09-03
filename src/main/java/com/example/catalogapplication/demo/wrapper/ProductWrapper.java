package com.example.catalogapplication.demo.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductWrapper {

    private int productId;
    private String productName;
    private int categoryId;
    private List<AttributeWrapper> attributes;
}
