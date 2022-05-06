package org.patikadev.orderexample.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.patikadev.orderexample.model.Category;


public record CreateCategoryRequestDTO(String name, Category parent) {

}
