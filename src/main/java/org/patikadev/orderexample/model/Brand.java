package org.patikadev.orderexample.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Brand extends BaseModel {

    private String name;

}
