package com.enigma.api.inventory.repositories;


import static org.junit.jupiter.api.Assertions.*;

import com.enigma.api.inventory.entities.Unit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UnitRepositoryTest {

    @Autowired
    private UnitRepository repository;

    @Test
    void shouldSave() {
        Unit unit = new Unit();
        unit.setCode("x");
        unit.setDescription("xas");

        repository.save(unit);

        Unit savedUnit = repository.findById(unit.getId()).get();
        assertEquals(unit, savedUnit);
    }

}
