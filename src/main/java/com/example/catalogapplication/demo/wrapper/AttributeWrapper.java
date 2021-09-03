package com.example.catalogapplication.demo.wrapper;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttributeWrapper {
    private int attributeId;
    private String attributeName;
    private String attributeValue;

}
