package com.enigma.api.inventory.controller;

import com.enigma.api.inventory.controllers.ItemController;
import com.enigma.api.inventory.controllers.TransactionController;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;
import com.enigma.api.inventory.entities.Transaction;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.services.*;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@WebMvcTest(TransactionController.class)
public class ControllerTransactionTest {

        @Autowired
        private MockMvc mvc;

        @MockBean
        private ItemService itemService;

        @MockBean
        private StockService stockService;

        @MockBean
        private TransactionService transactionService;

        @MockBean
        private ModelMapper modelMapper;

        @MockBean
        private FileService fileService;

//        @Test
//        public void addShouldSuccess() throws Exception {
//            Stock stock = new Stock();
//            stock.setId(1);
//            stock.setQuantity(10);
//            stock.setTotalPrice(1000);
//
//            Item item = new Item();
//            item.setId(1);
//            item.setName("orlan");
//            item.setPrice(1000);
//
//            Transaction entity = new Transaction();
//            entity.setItemId(item);
//            entity.setName("salman");
//            entity.setStockId(stock);
//
//            TransactionResponse transactionResponse = new TransactionResponse();
//            transactionResponse.getId();
//            transactionResponse.getItemId();
//            transactionResponse.getName();
//
//            when(modelMapper.map(any(TransactionRequest.class), any(Class.class))).thenReturn(entity);
//            when(itemService.findById(any(Integer.class))).thenReturn(item);
//            when(transactionService.save(any())).thenReturn(entity);
//            when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(transactionResponse);
//
//            RequestBuilder request = post("/transactions")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content("{\"name\": \"salman\",\"itemId\":1}");
//            mvc.perform(request)
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                    .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
//                    .andExpect(jsonPath("$.data.name", is(entity.getName())));
//        }
//
//    @Test
//    public void deleteShouldSuccess() throws Exception {
//
//        Stock stock = stockService.findById(1);
//
//        Transaction entity = new Transaction();
//        entity.setId(1);
//
//        TransactionResponse transactionResponse = new TransactionResponse();
//
//        when(transactionService.removeById(any(Integer.class))).thenReturn(entity);
//        when(stockService.findById(any(Integer.class))).thenReturn(stock);
//        when(stockService.save(any())).thenReturn(stock);
//        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(transactionResponse);
//
//        RequestBuilder request = delete("/transactions/1")
//                .contentType(MediaType.APPLICATION_JSON);
//        mvc.perform(request)
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
//                .andExpect(jsonPath("$.data.name", is(entity.getName())));
//    }

    @Test
    public void getByIdShouldSuccess() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("xas");

        UnitModel unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        Transaction entity = new Transaction();

        TransactionResponse transactionResponse = new TransactionResponse();


        when(transactionService.findById(any(Integer.class))).thenReturn(entity);
        when(modelMapper.map(any(Transaction.class), any(Class.class))).thenReturn(transactionResponse);

        RequestBuilder request = get("/transactions/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    public void allShouldReturnArray() throws Exception {
        Transaction entity = new Transaction();
        entity.setId(1);
        entity.setName("orlan");

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(entity);

        Page<Transaction> page = new PageImpl<>(listTransaction);

        when(modelMapper.map(any(UnitSearch.class), any(Class.class))).thenReturn(entity);
        when(transactionService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/transactions");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void allShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();
        when(transactionService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/transactions");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    public void getEntityNotNUlLException() throws Exception{
        when(transactionService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = get("/transactions/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }
    @Test
    public void deleteEntityNotNUlLException() throws Exception{
        when(transactionService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = delete("/transactions/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }
}
