package com.enigma.api.inventory.controller;

import com.enigma.api.inventory.controllers.QuantityController;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Quantity;
import com.enigma.api.inventory.models.ItemElement;
import com.enigma.api.inventory.models.QuantityRequest;
import com.enigma.api.inventory.models.QuantityResponse;
import com.enigma.api.inventory.models.UnitSearch;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.QuantityService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuantityController.class)
public class QuantityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private QuantityService service;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ItemService itemService;


    @Test
    public void addShouldSuccess() throws Exception {
        Item itemEntity = new Item();
        itemEntity.setId(1);
        itemEntity.setName("asd");
        itemEntity.setPrice(1000);

        ItemElement itemElement = new ItemElement();
        itemElement.setId(itemEntity.getId());
        itemElement.setName(itemEntity.getName());

        Quantity entity = new Quantity();
        entity.setId(1);
        entity.setQuantity(1000);
        entity.setItem(itemEntity);
        entity.setTotalPrice(10000);

        QuantityResponse quantityResponse = new QuantityResponse();
        quantityResponse.setId(entity.getId());
        quantityResponse.setQuantity(entity.getQuantity());
        quantityResponse.setItem(itemElement);

        when(modelMapper.map(any(QuantityRequest.class), any(Class.class))).thenReturn(entity);
        when(itemService.findById(any(Integer.class))).thenReturn(itemEntity);
        when(service.save(any())).thenReturn(entity);
        when(modelMapper.map(any(Quantity.class), any(Class.class))).thenReturn(quantityResponse);

        RequestBuilder request = post("/stocks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": 1, \"quantity\": 1000, \"totoalPrice\": 10000}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void editShouldSuccess() throws Exception {
        Item itemEntity = new Item();
        itemEntity.setId(1);
        itemEntity.setName("asd");
        itemEntity.setPrice(1000);

        ItemElement itemElement = new ItemElement();
        itemElement.setId(itemEntity.getId());
        itemElement.setName(itemEntity.getName());

        Quantity entity = new Quantity();
        entity.setId(1);
        entity.setQuantity(1000);
        entity.setItem(itemEntity);
        entity.setTotalPrice(10000);

        QuantityResponse quantityResponse = new QuantityResponse();
        quantityResponse.setId(entity.getId());
        quantityResponse.setQuantity(entity.getQuantity());
        quantityResponse.setItem(itemElement);


        when(service.findById(any(Integer.class))).thenReturn(entity);
        when(itemService.findById(any(Integer.class))).thenReturn(itemEntity);
        when(modelMapper.map(any(Quantity.class), any(Class.class))).thenReturn(quantityResponse);

        RequestBuilder request = put("/stocks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": 1, \"quantity\": 1000, \"totoalPrice\": 10000}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    public void deleteShouldSuccess() throws Exception {
        Item itemEntity = new Item();
        itemEntity.setId(1);
        itemEntity.setName("asd");
        itemEntity.setPrice(1000);

        ItemElement itemElement = new ItemElement();
        itemElement.setId(itemEntity.getId());
        itemElement.setName(itemEntity.getName());

        Quantity entity = new Quantity();
        entity.setId(1);
        entity.setQuantity(1000);
        entity.setItem(itemEntity);
        entity.setTotalPrice(10000);

        QuantityResponse quantityResponse = new QuantityResponse();
        quantityResponse.setId(entity.getId());
        quantityResponse.setQuantity(entity.getQuantity());
        quantityResponse.setItem(itemElement);

        when(service.removeById(any(Integer.class))).thenReturn(entity);
        when(modelMapper.map(any(Quantity.class), any(Class.class))).thenReturn(quantityResponse);

        RequestBuilder request = delete("/stocks/1")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    public void getByIdShouldSuccess() throws Exception {
        Item itemEntity = new Item();
        itemEntity.setId(1);
        itemEntity.setName("asd");
        itemEntity.setPrice(1000);

        ItemElement itemElement = new ItemElement();
        itemElement.setId(itemEntity.getId());
        itemElement.setName(itemEntity.getName());

        Quantity entity = new Quantity();
        entity.setId(1);
        entity.setQuantity(1000);
        entity.setItem(itemEntity);
        entity.setTotalPrice(10000);

        QuantityResponse quantityResponse = new QuantityResponse();
        quantityResponse.setId(entity.getId());
        quantityResponse.setQuantity(entity.getQuantity());
        quantityResponse.setItem(itemElement);


        when(service.findById(any(Integer.class))).thenReturn(entity);
        when(modelMapper.map(any(Quantity.class), any(Class.class))).thenReturn(quantityResponse);

        RequestBuilder request = get("/stocks/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.quantity", is(entity.getQuantity())));
    }

    @Test
    public void allShouldReturnArray() throws Exception {
        Quantity entity = new Quantity();
        entity.setId(1);
        entity.setQuantity(10);
        entity.setTotalPrice(10000);

        List<Quantity> listQuantity = new ArrayList<>();
        listQuantity.add(entity);

        Page<Quantity> page = new PageImpl<>(listQuantity);

        when(modelMapper.map(any(UnitSearch.class), any(Class.class))).thenReturn(entity);
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/stocks");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void allShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/stocks");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    public void allSummariesShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/stocks/summaries");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void getEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = get("/stocks/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }
    @Test
    public void deleteEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = delete("/stocks/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }

    @Test
    public void putEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/stocks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": , \"quantity\": , \"totoalPrice\": }");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())));

    }

    @Test
    public void putNameEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/stocks/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"itemId\": 1, \"quantity\": 1000, \"totoalPrice\": 10000}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }

}
