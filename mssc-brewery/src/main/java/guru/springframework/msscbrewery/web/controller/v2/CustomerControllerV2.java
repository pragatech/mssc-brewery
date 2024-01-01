package guru.springframework.msscbrewery.web.controller.v2;

import guru.springframework.msscbrewery.services.v2.CustomerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v2/customer/")
public class CustomerControllerV2{
    private final CustomerServiceV2 customerServiceV2;

    public CustomerControllerV2(CustomerServiceV2 customerServiceV2) {
        this.customerServiceV2 = customerServiceV2;
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDtoV2> getCustomer(
            @PathVariable("customerId") UUID id
    ){
        return new ResponseEntity<>(
                customerServiceV2.getCustomerId(id),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<CustomerDtoV2> handlePost(
            @Valid @RequestBody CustomerDtoV2 customerDtoV2
    ){
        CustomerDtoV2 dto = customerServiceV2.saveCustomer(customerDtoV2);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v2/customer/"+dto.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerDtoV2> handleUpdate(
            @PathVariable UUID customerId,
            @Valid @RequestBody CustomerDtoV2 customerDtoV2
    ){
        customerServiceV2.updateCustomer(customerId, customerDtoV2);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@ResponseStatus(HttpStatus.BAD_REQUEST) //-- Added for testing Spring boot auto validation of HTTP response
    public void deleteCustomer(
            @PathVariable UUID customerId
    ){
        customerServiceV2.deleteById(customerId);
    }
}

