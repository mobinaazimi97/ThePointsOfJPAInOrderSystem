package com.mftplus.ordersofcustomer.entity;


import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

@Entity(name = "OrderItemEntity")
@Table(name = "order_item_tbl")
@ToString(onlyExplicitlyIncluded = true)
public class OrderItem extends Base {
    @Id
    @SequenceGenerator(name = "orderItemSeq", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSeq")
    @ToString.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_order_id")
    @ToString.Exclude
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_product_id")
    @ToString.Include
    private Product product;

    @Column(name = "quantity")
    @ToString.Include
    private int quantity;

    @Column(name = "price")
    @ToString.Include
    private double price;

    @Transient
    @ToString.Include
    public double getAmount() {
        return quantity * price;
    }
}
