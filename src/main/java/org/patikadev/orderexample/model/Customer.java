package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Entity
@Getter
@Setter
public class Customer extends BaseExtendedModel {

    private String name;
    private String username;
    private String email;
    @Column(length = 11)
    private Long identity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private Set<Basket> baskets;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    private Set<Order> orders;
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddress customerAddress;

}
