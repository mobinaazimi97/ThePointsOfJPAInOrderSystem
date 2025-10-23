package com.mftplus.ordersofcustomer.groupProperty.entity;

import com.mftplus.ordersofcustomer.propertyValue.entity.ProductPropertyValue;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@ToString


@Entity(name = "groupProEntity")
@Table(name = "groupProperties")
public class GroupProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "value")
    private ProductPropertyValue productPropertyValue;
}
