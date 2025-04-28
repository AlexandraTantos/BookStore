package com.example.bookstore.service;

import com.example.bookstore.model.Customer;
import com.example.bookstore.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public Page<Customer> getAllCustomers(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public Customer getCustomerById(Long id){
        return  customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found!"));
    }

    public Customer registerCustomer(Customer customer){
        return  customerRepository.save(customer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
