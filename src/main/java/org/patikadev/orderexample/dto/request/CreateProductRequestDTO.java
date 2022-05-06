package org.patikadev.orderexample.dto.request;







import java.math.BigDecimal;


public record CreateProductRequestDTO(String createdBy,
                                      String name,
                                      BigDecimal price,
                                      String image,
                                      Long brandId,
                                      Long categoryId,
                                      int stock,
                                      Long sellerId,
                                      BigDecimal taxRate) {

}
