package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Campaign extends BaseModel{

    private BigDecimal discountRate;
    private BigDecimal discountPrice;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    @ManyToMany(mappedBy = "campaigns", cascade = CascadeType.PERSIST)
    private Set<Product> productSet = new HashSet<>();


}
