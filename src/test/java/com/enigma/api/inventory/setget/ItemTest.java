package com.enigma.api.inventory.setget;

import com.enigma.api.inventory.entities.*;
import com.enigma.api.inventory.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ItemTest {

    @Mock
    private Item item;

    @Mock
    private Stock stock;

    @Mock
    private Unit unit;

    @Test
    public void itemToString() {
        Item expected = new Item();
        expected.setCreatedDate(LocalDate.now());
        expected.setModifiedDate(LocalDate.now());
        Item actual = new Item();
        actual.setCreatedDate(LocalDate.now());
        actual.setModifiedDate(LocalDate.now());

        assertEquals(expected.getCreatedDate().toString(),actual.getCreatedDate().toString());
        assertEquals(expected.getModifiedDate(),actual.getModifiedDate());
        assertNotNull(expected.toString());
    }

    @Test
    public void getsetStockSummary() {
        StockSummary expected = new StockSummary();
        expected.setItem(item);
        expected.setQuantity(10L);
        expected.setTotalPrice(100L);

        StockSummary actual = new StockSummary();
        actual.setItem(item);
        actual.setQuantity(10L);
        actual.setTotalPrice(100L);

        assertEquals(expected.getItem(), actual.getItem());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getTotalPrice(),actual.getTotalPrice());
    }

    @Test
    public void getsetItemRequest() {
        ItemRequest expected = new ItemRequest();
        expected.setName("a");
        expected.setPrice(10);

        ItemRequest actual = new ItemRequest();
        actual.setName("a");
        actual.setPrice(10);

        assertEquals(expected.getName(),actual.getName());
        assertEquals(expected.getPrice(),actual.getPrice());
    }

    @Test
    public void getsetItemSearch(){
        ItemSearch expected = new ItemSearch();
        expected.setName("a");

        ItemSearch actual = new ItemSearch();
        actual.setName("a");

        assertEquals(expected.getName(),actual.getName());
    }

    @Test
    public void setgetPageList() {
        PagedList<Integer> expected = new PagedList<>();
        expected.setList(Collections.singletonList(1));
        expected.setPage(1);
        expected.setSize(1);
        expected.setTotal(1L);

        PagedList<Integer> actual = new PagedList<>();
        actual.setList(Collections.singletonList(1));
        actual.setPage(1);
        actual.setSize(1);
        actual.setTotal(1L);

        assertEquals(expected.getList(),actual.getList());
        assertEquals(expected.getPage(),actual.getPage());
        assertEquals(expected.getSize(),actual.getSize());
        assertEquals(expected.getTotal(),actual.getTotal());
    }

    @Test
    public void setgetPageSearch() {
        PageSearch expected = new PageSearch();
        expected.setPage(1);
        expected.setSort(Sort.Direction.ASC);
        expected.setSize(1);

        PageSearch actual = new PageSearch();
        actual.setPage(1);
        actual.setSort(Sort.Direction.ASC);
        actual.setSize(1);

        assertEquals(expected.getPage(),actual.getPage());
        assertEquals(expected.getSize(),actual.getSize());
    }
    @Test
    public void getsetStockElement() {
        StockElement expected = new StockElement();
        expected.setItemId(1);
        expected.setQuantity(10);
        expected.setTotalPrice(100);

        StockElement actual = new StockElement();
        actual.setItemId(1);
        actual.setQuantity(10);
        actual.setTotalPrice(100);

        assertEquals(expected.getItemId(), actual.getItemId());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getTotalPrice(),actual.getTotalPrice());
    }

    @Test
    public void getsetStockRequest() {
        StockRequest expected = new StockRequest();
        expected.setItemId(1);
        expected.setQuantity(10);
        expected.setTotalPrice(100);

        StockRequest actual = new StockRequest();
        actual.setItemId(1);
        actual.setQuantity(10);
        actual.setTotalPrice(100);

        assertEquals(expected.getItemId(), actual.getItemId());
        assertEquals(expected.getQuantity(), actual.getQuantity());
        assertEquals(expected.getTotalPrice(),actual.getTotalPrice());
    }

    @Test
    public void getsetStockSearch() {
        StockSearch expected = new StockSearch();
        expected.setQuantity("10");

        StockSearch actual = new StockSearch();
        actual.setQuantity("10");

        assertEquals(expected.getQuantity(),actual.getQuantity());
    }

    @Test
    public void getsetUnitSearch() {
        UnitSearch unitSearch = new UnitSearch();
        unitSearch.setCode("a");
        unitSearch.setDescription("asd");

        UnitSearch actual = new UnitSearch();
        actual.setCode("a");
        actual.setDescription("asd");

        assertEquals(unitSearch.getCode(), actual.getCode());
        assertEquals(unitSearch.getDescription(), actual.getDescription());
    }

    @Test
    public void getsetTransaction() {
        Transaction expected = new Transaction();
        expected.setStockId(stock);
        expected.setId(1);
        expected.setItemId(item);
        expected.setName("a");

        Transaction actual = new Transaction();
        actual.setItemId(item);
        actual.setId(1);
        actual.setStockId(stock);
        actual.setName("a");

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getItemId(), actual.getItemId());
        assertEquals(expected.getStockId(),actual.getStockId());
        assertEquals(expected.getName(),actual.getName());

        assertNotNull(expected.toString());
    }


}
