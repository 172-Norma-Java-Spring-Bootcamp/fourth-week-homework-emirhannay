package org.patikadev.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Product extends BaseExtendedModel {


    private String name;
    private BigDecimal price;
    private UUID barcode;
    private String image;

    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Seller seller;

    private int stock;

    private BigDecimal taxRate;

    @ManyToMany
    @JoinTable(name = "PRODUCT_CAMPAIGN", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "campaign_id") })
    private Set<Campaign> campaigns = new HashSet<>();




}
