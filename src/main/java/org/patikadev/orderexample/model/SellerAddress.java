package org.patikadev.orderexample.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class SellerAddress extends BaseModel{

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;

    @JsonIgnore
    @OneToOne
    private Seller seller;
}
