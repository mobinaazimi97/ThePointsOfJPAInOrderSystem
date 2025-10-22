package com.mftplus.ordersofcustomer.order.entity;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import com.mftplus.ordersofcustomer.order.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity(name = "orderEntity")
@Table(name = "orders")
public class Order extends Base {
    @Id
    @SequenceGenerator(name = "orderSeq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "serial_number", unique = true)
    private String serial;

    @Column(name = "Date_Time") //nullable = false
    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "tax", length = 10) //nullable = false
    private double tax;

    @Column(name = "shipping_cost") //nullable = false
    private double shippingCost;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItem);
    }

    @Column(name = "bill_address")//, nullable = false
    private String billingAddress;

    private double totalAmount;
    private double pureAmount;
    private double discount;

    public double getTotalAmount() {
        orderItems.forEach(item -> totalAmount += item.getAmount());
        return totalAmount;
    }

    public double getPureAmount() {
        pureAmount = getTotalAmount() - discount;
        return pureAmount;
    }

    @PrePersist
    @PreUpdate
    public void setDateTime() {
        orderDateTime = LocalDateTime.now();
    }

}
