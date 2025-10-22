package com.mftplus.ordersofcustomer.inheritanceModel;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@MappedSuperclass
@ToString

public class Base {
    @Version
    private Long versionId;
    @Transient
    private boolean editing = false;
    @Transient
    private boolean deleted = false;
    @Transient
    private String createdBy;
    @Transient
    private String updatedBy;
    @Transient
    private String createdDate;
    @Transient
    private boolean locked;
}
