package guru.springframework.msscbrewery.services.v2;

import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface CustomerServiceV2 {
    CustomerDtoV2 getCustomerId(UUID id);

    CustomerDtoV2 saveCustomer(CustomerDtoV2 customerDtoV2);

    void updateCustomer(UUID customerId, CustomerDtoV2 customerDtoV2);

    void deleteById(UUID customerId);
}
