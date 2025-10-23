package com.mftplus.ordersofcustomer.productGroup.entity;

import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import com.mftplus.ordersofcustomer.groupProperty.entity.GroupProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productGroupEntity")
@Table(name = "productGroups")
public class ProductGroup extends Base {
    @Id
    @SequenceGenerator(name = "productGroupSeq", sequenceName = "productGroup_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productGroupSeq")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
//    @Pattern(regexp = "^[a-zA-Z]{3,30}$", message = "invalid name!")
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
    @Column(name = "productGroup-child")
    private List<ProductGroup> childList=new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private ProductGroup parent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private GroupProperty groupProperty;

}


