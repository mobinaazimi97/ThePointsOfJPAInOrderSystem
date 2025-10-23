package com.mftplus.ordersofcustomer.order.entity;


import com.mftplus.ordersofcustomer.customer.entity.Customer;
import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import com.mftplus.ordersofcustomer.order.entity.enums.OrderStatus;
import com.mftplus.ordersofcustomer.orderItem.entity.OrderItem;
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
@Entity(name = "orderEntity")
@Table(name = "orders")
@ToString(onlyExplicitlyIncluded = true)
public class Order extends Base {
    @Id
    @SequenceGenerator(name = "orderSeq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    @ToString.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @ToString.Include
    private Customer customer;

    @Column(name = "serial_number", unique = true)
    @ToString.Include
    private String serial;

    @Column(name = "Date_Time")
    @ToString.Include
    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @ToString.Include
    private OrderStatus orderStatus;

    @Column(name = "tax", length = 10)
    @ToString.Include
    private double tax;

    @Column(name = "shipping_cost")
    @ToString.Include
    private double shippingCost;

    @Column(name = "discount")
    @ToString.Include
    private double discount;

    @Column(name = "bill_address")
    @ToString.Include
    private String billingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    @ToString.Include
    private List<OrderItem> orderItems;

    public void addItem(OrderItem orderItem) {
        if (orderItems == null) orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }


    @Transient
    @ToString.Include
    public double getTotalAmount() {
        return orderItems == null ? 0 :
                orderItems.stream().mapToDouble(OrderItem::getAmount).sum();
    }

    @Transient
    @ToString.Include
    public double getPureAmount() {
        return getTotalAmount() - discount;
    }

    @PrePersist
    @PreUpdate
    public void setDateTime() {
        orderDateTime = LocalDateTime.now();
    }

}
