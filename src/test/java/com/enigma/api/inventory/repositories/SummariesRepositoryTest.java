package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.StockSummary;
import com.enigma.api.inventory.repositories.impl.StockSummaryRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class SummariesRepositoryTest {

    @Autowired
    StockSummaryRepositoryImpl stockSummaryRepositoryss;

    @Test
    public void summaries() {
        List<StockSummary> stockSummaryList = stockSummaryRepositoryss.findAllSummaries();
//
        assertNotNull(stockSummaryList);
    }
}
