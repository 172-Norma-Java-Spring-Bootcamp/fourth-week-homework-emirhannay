package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Seller extends BaseExtendedModel{

    private String name;
    private String email;
    private String username;
    private String password;
    @OneToOne(mappedBy = "seller",cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    private SellerAddress sellerAddress;
    private BigDecimal shippingCost;
}
