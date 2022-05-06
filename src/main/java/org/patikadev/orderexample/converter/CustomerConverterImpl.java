package org.patikadev.orderexample.converter;

import org.patikadev.orderexample.dto.request.CreateCustomerRequestDTO;
import org.patikadev.orderexample.dto.request.CustomerAddressDTO;
import org.patikadev.orderexample.dto.response.GetCustomersResponseDTO;
import org.patikadev.orderexample.model.Customer;
import org.patikadev.orderexample.model.CustomerAddress;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public
class CustomerConverterImpl implements CustomerConverter {

    @Override
    public Customer toCustomer(CreateCustomerRequestDTO createCustomerRequestDTO) {

        Customer customer = new Customer();
        customer.setUsername(createCustomerRequestDTO.userName());
        customer.setEmail(createCustomerRequestDTO.email());
        customer.setIdentity(createCustomerRequestDTO.identity());
        customer.setGender(createCustomerRequestDTO.gender());
        customer.setPassword(createCustomerRequestDTO.password());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy(createCustomerRequestDTO.createdBy());

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(createCustomerRequestDTO.customerAddress().phoneNumber());
        customerAddress.setCountry(createCustomerRequestDTO.customerAddress().country());
        customerAddress.setCity(createCustomerRequestDTO.customerAddress().city());
        customerAddress.setPostalCode(createCustomerRequestDTO.customerAddress().postalCode());
        customerAddress.setDescription(createCustomerRequestDTO.customerAddress().description());

        customerAddress.setCustomer(customer);
        customer.setCustomerAddress(customerAddress);

        return customer;
    }

    @Override
    public CreateCustomerRequestDTO toCreateCustomerRequest(Customer customer) {
        return new CreateCustomerRequestDTO(customer.getCreatedBy(),customer.getUsername(),
                customer.getEmail(),
                customer.getIdentity(),
                customer.getGender(),
                customer.getPassword(),
                convertCustomerAddressDto(customer.getCustomerAddress()));

    }

    @Override
    public GetCustomersResponseDTO toGetCustomersResponse(Customer customer) {
        return new GetCustomersResponseDTO(customer.getId(),
                customer.getUsername(),
                customer.getEmail(),
                customer.getGender(),
                convertCustomerAddressDto(customer.getCustomerAddress()));

    }

    private CustomerAddressDTO convertCustomerAddressDto(CustomerAddress customerAddress) {
        return new CustomerAddressDTO(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }
}
