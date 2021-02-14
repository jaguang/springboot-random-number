package com.enigma.api.inventory.services.impl;

import com.enigma.api.inventory.entities.Customer;
import com.enigma.api.inventory.repositories.CustomerRepository;
import com.enigma.api.inventory.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerSeviceImpl extends CommonServiceImpl<Customer,Integer> implements CustomerService {

    @Autowired
    public CustomerSeviceImpl(CustomerRepository customerRepository) {
        super(customerRepository);
    }
}
