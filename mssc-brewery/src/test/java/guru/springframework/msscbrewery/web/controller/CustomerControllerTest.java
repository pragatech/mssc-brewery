package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDto validCustomer;

    @Before
    public void setUp() {
        validCustomer = CustomerDto.builder().id(UUID.randomUUID()).name("Test Customer").build();
    }

    @Test
    public void getCustomer() throws Exception{
        given(customerService.getCustomerId(any())).willReturn(validCustomer);

        mockMvc.perform(get("/api/v1/customer/"+validCustomer.getId().toString(), validCustomer.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Test Customer")));
    }


    @Test
    public void saveCustomer() throws Exception{
        CustomerDto customerDto = validCustomer;
        customerDto.setId(null);
        CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).name("New Customer").build();
        String jsonDto = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveCustomer(any())).willReturn(savedDto);

        mockMvc.perform(
                post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonDto))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomer() throws Exception{
        CustomerDto customerDto = validCustomer;
        String customerJsonDto = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(
                put("/api/v1/customer/"+customerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJsonDto)
        )
                .andExpect(status().isNoContent());
        then(customerService).should().updateCustomer(any(), any());
    }

    @Test
    public void deleteCustomer() throws Exception{
        mockMvc.perform(
                        delete("/api/v1/customer/" + validCustomer.getId().toString()))
                .andExpect(status().isNoContent());
        then(customerService).should().deleteById(any());
    }
}