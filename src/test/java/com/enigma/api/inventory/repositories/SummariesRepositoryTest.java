package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.QuantitySummary;
import com.enigma.api.inventory.repositories.impl.QuantitySummaryRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class SummariesRepositoryTest {

    @Autowired
    QuantitySummaryRepositoryImpl stockSummaryRepositoryss;

    @Test
    public void summaries() {
        List<QuantitySummary> quantitySummaryList = stockSummaryRepositoryss.findAllSummaries();
//
        assertNotNull(quantitySummaryList);
    }
}
