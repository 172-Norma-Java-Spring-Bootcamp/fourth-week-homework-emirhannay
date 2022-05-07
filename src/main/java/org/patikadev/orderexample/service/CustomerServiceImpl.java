package org.patikadev.orderexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.patikadev.orderexample.converter.CustomerConverter;
import org.patikadev.orderexample.dto.request.CreateCustomerRequestDTO;
import org.patikadev.orderexample.dto.response.GetCustomersResponseDTO;
import org.patikadev.orderexample.exception.BaseException;
import org.patikadev.orderexample.exception.BusinessServiceOperationException;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.repository.BasketRepository;
import org.patikadev.orderexample.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;
    private final BasketRepository basketRepository;

    @Override
    public void create(CreateCustomerRequestDTO createCustomerRequestDTO) {
        Customer customer = customerConverter.toCustomer(createCustomerRequestDTO);
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        log.info("Getting customer was successfully -> {}",customer.getId());
        return customer;
    }
    @Override
    public CreateCustomerRequestDTO getCustomerDto(Long id) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        log.info("Getting customer was successfully -> {}",customer.getId());
        return customerConverter.toCreateCustomerRequest(customer);
    }

    @Override
    public Collection<GetCustomersResponseDTO> getCustomers() {
        Collection<GetCustomersResponseDTO> getCustomersResponseDTO = customerRepository
                .findAllCustomersByDeleteStatusByJPQL(false).orElseThrow(
                        () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer list not found")
                )
                .stream()
                .map(customerConverter::toGetCustomersResponse)
                .toList();
        log.info("Getting customers was successfully ");
        return getCustomersResponseDTO;
    }

    @Override
    public void delete(Long id, boolean hardDelete) throws BaseException {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found"));
        if (hardDelete) {
            customerRepository.delete(customer);
            log.info("Hard delete customer was successfully -> {}",customer.getId());
            return;
        }
        if (customer.isDeleted()) {

            throw new BusinessServiceOperationException.CustomerAlreadyDeletedException("Customer already deleted");
        }


        customer.setDeleted(true);
        customerRepository.save(customer);
        log.info("Delete customer was successfully -> {}",customer.getId());
    }


}
