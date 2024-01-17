package guru.springframework.msscbrewery.web.mappers;

import guru.springframework.msscbrewery.domain.v2.CustomerV2;
import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerV2 customerDtoToCustomer(CustomerDtoV2 customerDtoV2);
    CustomerDtoV2 customerToCustomerDto(CustomerV2 customerV2);
}
