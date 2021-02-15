package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Quantity;
import com.enigma.api.inventory.entities.QuantitySummary;

import java.util.List;

public interface QuantityService extends CommonService<Quantity, Integer>{

    List<QuantitySummary> findAllSummaries();

}
