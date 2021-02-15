package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.QuantitySummary;
import java.util.List;

// interface ini diextend interface stock repository
public interface QuantitySummaryRepository {

    List<QuantitySummary> findAllSummaries();
}
