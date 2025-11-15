package com.mftplus.ordersofcustomer.entity;

import com.mftplus.ordersofcustomer.inheritanceModel.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "productEntity")
@Table(name = "products")
public class Product extends Base {
    @Id
    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    private Long id;

    @Column(name = "product_name", length = 30) //todo nullable...
//  @Pattern(regexp = "^[a-zA-Z]{3,30}$",message = "invalid name!")
    private String name;

    @Column(name = "price")
//    @Pattern(regexp = "^[0-9]{2,30}$",message = "invalid price")
    @Min(value = 0, message = "Cant be negative")
    private Float price;

    @Column(name = "product_code", length = 10)
    private String code;

    @Column(name = "category", length = 30)
    private String category;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "p_group")
    private ProductGroup productGroup;

    @Column(name = "expiredDate")
    private LocalDate expiredDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images;


    @PrePersist
    @PreUpdate
    public void setDate() {
        expiredDate = LocalDate.now();
    }

}
