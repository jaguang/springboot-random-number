package com.enigma.api.inventory.services.impl;

import com.enigma.api.inventory.entities.Quantity;
import com.enigma.api.inventory.entities.QuantitySummary;
import com.enigma.api.inventory.repositories.QuantityRepository;
import com.enigma.api.inventory.services.QuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityServiceImpl extends CommonServiceImpl<Quantity, Integer> implements QuantityService {

    @Autowired
    public QuantityServiceImpl(QuantityRepository quantityRepository) {
        super(quantityRepository);
    }

    @Override
    public List<QuantitySummary> findAllSummaries() {
        return ((QuantityRepository) repository).findAllSummaries();
    }
}
