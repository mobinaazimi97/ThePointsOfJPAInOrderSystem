package com.mftplus.ordersofcustomer.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class ProductGroupDTO {
    private Long id;
    private String name;
    private Long parentId;
    private String parentTitle;
    private List<Long> childrenIds = new ArrayList<>();

//    private GroupProperty groupProperty;
}
