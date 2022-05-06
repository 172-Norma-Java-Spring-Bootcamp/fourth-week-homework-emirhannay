package org.patikadev.orderexample.dto.request;

import java.math.BigDecimal;
import java.util.Date;

public record CreateCampaignRequestDTO(BigDecimal discountRate,
                                       BigDecimal discountPrice,
                                       String name,
                                       String description,
                                       Date startDate,
                                       Date endDate) {
}
