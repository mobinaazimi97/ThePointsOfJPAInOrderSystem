package com.mftplus.ordersofcustomer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity(name = "reportEntity")
@Table(name = "reports")
public class Report {

    @Id
    @SequenceGenerator(name = "repSeq", sequenceName = "report_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "repSeq")
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(name = "GENERATED_AT", nullable = false)
    private LocalDateTime generatedAt;

    public LocalDate getFromDate() {
        return LocalDate.of(year, month, 1);
    }

    public LocalDate getToDate() {
        return getFromDate().plusMonths(1);
    }
}
