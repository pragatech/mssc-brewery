package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerId(UUID customerId);

    void deleteById(UUID customerId);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    CustomerDto saveCustomer(CustomerDto customerDto);
}
