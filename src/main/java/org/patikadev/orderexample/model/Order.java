package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order_detail")
public class Order extends BaseExtendedModel{
            private BigDecimal price;
            @Enumerated(EnumType.STRING)
            private OrderStatus orderStatus;
            @OneToOne
            private Customer customer;
            @OneToOne(cascade = CascadeType.MERGE)
            private Basket basket;
            @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
            private OrderAddress orderAddress;
}