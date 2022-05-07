package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "order_detail")
public class Order extends BaseExtendedModel{
            private BigDecimal price;
            @Enumerated(EnumType.STRING)
            private OrderStatus orderStatus;
            @ManyToOne
            @JoinColumn(name = "customer_id", nullable = false)
            private Customer customer;
            @OneToOne(cascade = CascadeType.REMOVE)
            private Basket basket;
            @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
            private OrderAddress orderAddress;
            private Date orderTime;
}
