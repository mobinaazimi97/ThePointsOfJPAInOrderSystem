package com.mftplus.ordersofcustomer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@NoArgsConstructor
@SuperBuilder
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthlySalesReportDTO {

    private Long orderId;
    private String orderSerial;
    private String productName;
    private int quantity;
    private double amount;
}
