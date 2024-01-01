package guru.springframework.msscbrewery.web.controller.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.v2.CustomerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.CustomerDtoV2;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerControllerV2.class)
public class CustomerControllerV2Test {

    @MockBean
    private CustomerServiceV2 customerServiceV2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDtoV2 validCustomer;

    @Before
    public void setUp() {
        validCustomer = CustomerDtoV2.builder().id(UUID.randomUUID()).name("Test Customer").build();
    }

    public CustomerControllerV2Test() {
    }

    @Test
    public void getCustomer() throws Exception{
        given(customerServiceV2.getCustomerId(any())).willReturn(validCustomer);

        mockMvc.perform(get("/api/v2/customer/"+validCustomer.getId().toString(), validCustomer.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Test Customer")));
    }


    @Test
    public void saveCustomer() throws Exception{
        CustomerDtoV2 customerDtoV2 = validCustomer;
        customerDtoV2.setId(null);
        CustomerDtoV2 savedDto = CustomerDtoV2.builder().id(UUID.randomUUID()).name("New Customer").build();
        String jsonDto = objectMapper.writeValueAsString(customerDtoV2);

        given(customerServiceV2.saveCustomer(any())).willReturn(savedDto);

        mockMvc.perform(
                        post("/api/v2/customer/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonDto))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateCustomer() throws Exception{
        CustomerDtoV2 customerDtoV2 = validCustomer;
        String customerJsonDto = objectMapper.writeValueAsString(customerDtoV2);

        mockMvc.perform(
                        put("/api/v2/customer/"+customerDtoV2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(customerJsonDto)
                )
                .andExpect(status().isNoContent());
        then(customerServiceV2).should().updateCustomer(any(), any());
    }

    @Test
    public void deleteCustomer() throws Exception{
        mockMvc.perform(
                        delete("/api/v2/customer/" + validCustomer.getId().toString()))
                .andExpect(status().isNoContent());
        then(customerServiceV2).should().deleteById(any());
    }
}
