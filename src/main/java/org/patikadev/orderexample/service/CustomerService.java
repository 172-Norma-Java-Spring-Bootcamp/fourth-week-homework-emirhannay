package org.patikadev.orderexample.service;

import org.patikadev.orderexample.dto.request.CreateCustomerRequestDTO;
import org.patikadev.orderexample.dto.response.GetCustomersResponseDTO;
import org.patikadev.orderexample.exception.BaseException;
import org.patikadev.orderexample.model.Customer;

import java.util.Collection;

public interface CustomerService {
    void create(CreateCustomerRequestDTO customerDTO);

    Customer getCustomer(Long id) throws BaseException;
    CreateCustomerRequestDTO getCustomerDto(Long id) throws BaseException;

    Collection<GetCustomersResponseDTO> getCustomers() throws BaseException;

    void delete(Long id, boolean hardDelete) throws BaseException;



}
