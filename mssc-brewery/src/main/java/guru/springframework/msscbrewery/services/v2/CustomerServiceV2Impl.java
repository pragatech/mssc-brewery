package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class CustomerServiceV2Impl implements CustomerServiceV2 {
    @Override
    public CustomerDtoV2 getCustomerId(UUID customerId) {
        return CustomerDtoV2.builder()
                .id(customerId)
                .name("Praga")
                .build();
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("delete customer "+customerId);
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDtoV2 customerDto) {
        //TODO
    }

    @Override
    public CustomerDtoV2 saveCustomer(CustomerDtoV2 customerDtoV2) {
        return CustomerDtoV2.builder()
                .id(UUID.randomUUID())
                .name(customerDtoV2.getName())
                .build();
    }
}
