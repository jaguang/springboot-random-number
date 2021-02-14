package com.enigma.api.inventory.controller;

import com.enigma.api.inventory.controllers.ItemController;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.ItemRequest;
import com.enigma.api.inventory.models.ItemResponse;
import com.enigma.api.inventory.models.UnitModel;
import com.enigma.api.inventory.models.UnitSearch;
import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.UnitService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private UnitService unitService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private FileService fileService;

        @Test
        public void addShouldSuccess() throws Exception {
            Unit unit = new Unit();
            unit.setId(1);
            unit.setCode("x");
            unit.setDescription("xas");

            UnitModel unitModel = new UnitModel();
            unitModel.setId(unit.getId());
            unitModel.setCode(unit.getCode());
            unitModel.setDescription(unit.getDescription());

            Item entity = new Item();
            entity.setId(1);
            entity.setName("orlan");
            entity.setPrice(1000);
            entity.setUnit(unit);

            ItemResponse itemResponse = new ItemResponse();
            itemResponse.setId(entity.getId());
            itemResponse.setName(entity.getName());
            itemResponse.setPrice(entity.getPrice());
            itemResponse.setUnit(unitModel);

            when(modelMapper.map(any(ItemRequest.class), any(Class.class))).thenReturn(entity);
            when(unitService.findById(any(Integer.class))).thenReturn(unit);
            when(itemService.save(any())).thenReturn(entity);
            when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(itemResponse);

            RequestBuilder request = post("/items")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"unitId\": 1, \"name\": \"" + entity.getName() + "\", \"price\": " + entity.getPrice() + "}");
            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                    .andExpect(jsonPath("$.data.name", is(entity.getName())));
        }
    @Test
    public void editShouldSuccess() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("xas");

        UnitModel unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        Item entity = new Item();
        entity.setId(1);
        entity.setName("orlan");
        entity.setPrice(1000);
        entity.setUnit(unit);

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(entity.getId());
        itemResponse.setName(entity.getName());
        itemResponse.setPrice(entity.getPrice());
        itemResponse.setUnit(unitModel);

        when(itemService.findById(any(Integer.class))).thenReturn(entity);
        when(unitService.findById(any(Integer.class))).thenReturn(unit);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(itemResponse);

        RequestBuilder request = put("/items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"unitId\": 1, \"name\": \"" + entity.getName() + "\", \"price\": " + entity.getPrice() + "}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    public void deleteShouldSuccess() throws Exception {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setCode("x");
        unit.setDescription("xas");

        UnitModel unitModel = new UnitModel();
        unitModel.setId(unit.getId());
        unitModel.setCode(unit.getCode());
        unitModel.setDescription(unit.getDescription());

        Item entity = new Item();
        entity.setId(1);
        entity.setName("orlan");
        entity.setPrice(1000);
        entity.setUnit(unit);

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(entity.getId());
        itemResponse.setName(entity.getName());
        itemResponse.setPrice(entity.getPrice());
        itemResponse.setUnit(unitModel);

        when(itemService.removeById(any(Integer.class))).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(itemResponse);

        RequestBuilder request = delete("/items/1")
                .contentType(MediaType.APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

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

        Item entity = new Item();
        entity.setId(1);
        entity.setName("orlan");
        entity.setPrice(1000);
        entity.setUnit(unit);

        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setId(entity.getId());
        itemResponse.setName(entity.getName());
        itemResponse.setPrice(entity.getPrice());
        itemResponse.setUnit(unitModel);

        when(itemService.findById(any(Integer.class))).thenReturn(entity);
        when(modelMapper.map(any(Item.class), any(Class.class))).thenReturn(itemResponse);

        RequestBuilder request = get("/items/1");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.name", is(entity.getName())));
    }

    @Test
    public void allShouldReturnArray() throws Exception {
        Item entity = new Item();
        entity.setId(1);
        entity.setName("orlan");
        entity.setPrice(1000);

        List<Item> listItem = new ArrayList<>();
        listItem.add(entity);

        Page<Item> page = new PageImpl<>(listItem);

        when(modelMapper.map(any(UnitSearch.class), any(Class.class))).thenReturn(entity);
        when(itemService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/items");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void allShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();
        when(itemService.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/items");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    public void getEntityNotNUlLException() throws Exception{
        when(itemService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = get("/items/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }
    @Test
    public void deleteEntityNotNUlLException() throws Exception{
        when(itemService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = delete("/items/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }

    @Test
    public void putEntityNotNUlLException() throws Exception{
        when(itemService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/items/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"\",\"price\": \"\",\"unitId\": \"\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())));

    }

    @Test
    public void putNameEntityNotNUlLException() throws Exception{
        when(itemService.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/items/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"orlan\",\"price\": \"1000\",\"unitId\": \"1\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }

}

