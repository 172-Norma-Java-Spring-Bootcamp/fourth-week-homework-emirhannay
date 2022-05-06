package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class OrderAddress extends BaseModel {

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;

    @OneToOne
    private Order order;
}
