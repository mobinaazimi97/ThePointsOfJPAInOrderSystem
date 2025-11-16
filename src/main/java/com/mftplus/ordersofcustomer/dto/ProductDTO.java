package com.mftplus.ordersofcustomer.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private Float price;
    private String code;
    private String category;
    private LocalDate expiredDate;

//    private ProductGroupDTO productGroup;

}
