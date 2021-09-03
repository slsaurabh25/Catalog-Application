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
public class CategoryWrapper {
    private int categoryId;
    private String categoryName;
    private List<AttributeWrapper> attributes;
}
