package org.patikadev.orderexample.model;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon extends BaseModel {
    private BigDecimal discountRate;
    private BigDecimal discountPrice;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
}
