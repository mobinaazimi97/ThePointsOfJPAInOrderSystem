package com.mftplus.ordersofcustomer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ReportSummary {
    private Long orderCount;
    private Double totalSales;

    public ReportSummary() {
    }

    public ReportSummary(Long orderCount, Double totalSales) {
        this.orderCount = orderCount;
        this.totalSales = totalSales;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return "ReportSummary{" +
                "orderCount=" + orderCount +
                ", totalSales=" + totalSales +
                '}';
    }
}
