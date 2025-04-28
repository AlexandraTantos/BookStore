package com.example.bookstore.service;

import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;
    private Page<Customer> customerPage;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        customer = new Customer("test@yahoo.com");
        customerPage = new PageImpl<>(Arrays.asList(customer));
    }

    @Test
    void testSearchCustomerByEmail(){
        when(customerRepository.findByEmail("test@yahoo.com")).thenReturn(customer);

        Customer result = customerService.getCustomerByEmail("test@yahoo.com");
        assertNotNull(result);
        assertEquals("test@yahoo.com",result.getEmail());
    }

    @Test
    void testGetAllCustomers(){
        when(customerRepository.findAll(PageRequest.of(0,10))).thenReturn(customerPage);

        Page<Customer> result = customerService.getAllCustomers(PageRequest.of(0,10));
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("test@yahoo.com", result.getContent().get(0).getEmail());
    }

    @Test
    void testGetCustomerById(){
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals("test@yahoo.com",result.getEmail());
    }

    @Test
    void testRegisterCustomer(){
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer result = customerService.registerCustomer(customer);

        assertNotNull(result);
        assertEquals("test@yahoo.com", result.getEmail());
        verify(customerRepository,times(1)).save(customer);
    }

    @Test
    void testDeleteCustomer(){
        doNothing().when(customerRepository).deleteById(1L);

        customerService.deleteCustomer(1L);
        verify(customerRepository,times(1)).deleteById(1L);
    }
}
