package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer/")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(
            @PathVariable("customerId") UUID id
    ){
        return new ResponseEntity<>(
                customerService.getCustomerId(id),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<CustomerDto> handlePost(
           @RequestBody CustomerDto customerDto
    ){
        CustomerDto dto = customerService.saveCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/"+dto.getId().toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerDto> handleUpdate(
        @PathVariable UUID customerId,
        @RequestBody CustomerDto customerDto
    ){
        customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    //@ResponseStatus(HttpStatus.BAD_REQUEST) //-- Added for testing Spring boot auto validation of HTTP response
    public void deleteCustomer(
           @PathVariable UUID customerId
    ){
        customerService.deleteById(customerId);
    }
}
