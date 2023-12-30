package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{
    @Override
    public CustomerDto getCustomerId(UUID customerId) {
        return CustomerDto.builder()
                .id(customerId)
                .name("Praga")
                .build();
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("delete customer "+customerId);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        //TODO
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }
}
