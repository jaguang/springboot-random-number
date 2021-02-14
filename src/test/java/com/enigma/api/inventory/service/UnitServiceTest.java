package com.enigma.api.inventory.service;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.ItemRepository;
import com.enigma.api.inventory.repositories.UnitRepository;
import com.enigma.api.inventory.services.impl.ItemServiceImpl;
import com.enigma.api.inventory.services.impl.UnitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {


    @InjectMocks
    private ItemServiceImpl itemService;

    @InjectMocks
    private UnitServiceImpl service;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UnitRepository repository;

    @Test
    public void shouldSave() {
        Unit input = new Unit();
        input.setCode("x");
        input.setDescription("xas");

        Unit output = new Unit();
        output.setId(1);
        output.setCode("x");
        output.setDescription("xas");

        when(repository.save(any()))
                .thenReturn(output);

        Unit result = service.save(input);

        assertEquals(output, result);

    }

//    @Test
//    void shouldSaveItemAndUnit() {
//
//        Item input = new Item();
//
//        input.setName("x");
//        input.setPrice(200);
//
//        Item output = new Item();
//        output.setId(1);
//        output.setName("x");
//        output.setPrice(200);
//
//        when(itemRepository.save(any()))
//                .thenReturn(output);
//
//        Item result = itemRepository.save(input);
//
//        assertEquals(output, result);
//
//    }
}
