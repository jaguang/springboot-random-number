package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.StockSummary;
import java.util.List;

// interface ini diextend interface stock repository
public interface StockSummaryRepository {

    List<StockSummary> findAllSummaries();
}
