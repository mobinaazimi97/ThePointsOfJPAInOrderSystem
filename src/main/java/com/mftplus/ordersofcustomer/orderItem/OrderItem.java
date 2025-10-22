package com.mftplus.ordersofcustomer.orderItem;


import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import com.mftplus.ordersofcustomer.order.entity.Order;
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
@ToString

@Entity(name = "OrderItemEntity")
@Table(name = "order_item_tbl")
public class OrderItem extends Base {
    @Id
    @SequenceGenerator(name = "orderItemSeq", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_order_id")
    private Order order;

    @Transient
    private double amount;
    private int quantity;
    private double price;

    public double getAmount() {
        amount = quantity * price;
        return amount;
    }

}
