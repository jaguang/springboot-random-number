package com.enigma.api.inventory.controller;

import com.enigma.api.inventory.controllers.UnitController;
import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.models.UnitModel;
import com.enigma.api.inventory.models.UnitSearch;
import com.enigma.api.inventory.services.UnitService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import static org.mockito.Mockito.when;;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UnitController.class)
public class UnitControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnitService service;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void addShouldSucces() throws Exception {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("x");
        entity.setDescription("xas");
        when(service.save(any())).thenReturn(entity);

        UnitModel model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        when(modelMapper.map(any(Unit.class),any(Class.class))).thenReturn(model);

        RequestBuilder request = post("/units")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"x\",\"description\": \"xas\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    public void editShouldSucces() throws Exception {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("x");
        entity.setDescription("xas");
        when(service.findById(any())).thenReturn(entity);
        when(service.save(any())).thenReturn(entity);

        UnitModel model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        when(modelMapper.map(any(Unit.class),any(Class.class))).thenReturn(model);

        RequestBuilder request = put("/units/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"x\",\"description\": \"xas\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    public void removeShouldSucces() throws Exception {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("x");
        entity.setDescription("xas");
        when(service.save(any())).thenReturn(entity);

        UnitModel model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        when(modelMapper.map(any(Unit.class),any(Class.class))).thenReturn(model);
        when(service.removeById(1)).thenReturn(entity);

        RequestBuilder request = delete("/units/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"x\",\"description\": \"xas\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }

    @Test
    public void findShouldSucces() throws Exception {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("x");
        entity.setDescription("xas");
        when(service.save(any())).thenReturn(entity);
        when(service.findById(1)).thenReturn(entity);

        UnitModel model = new UnitModel();
        model.setId(entity.getId());
        model.setCode(entity.getCode());
        model.setDescription(entity.getDescription());
        when(modelMapper.map(any(Unit.class),any(Class.class))).thenReturn(model);


        RequestBuilder request = get("/units/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"x\",\"description\": \"xas\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.code", is(entity.getCode())));
    }
    @Test
    public void allShouldReturnArray() throws Exception {
        Unit entity = new Unit();
        entity.setId(1);
        entity.setCode("x");
        entity.setDescription("xas");

        List<Unit> listUnit = new ArrayList<>();
        listUnit.add(entity);

        Page<Unit> page = new PageImpl<>(listUnit);

        when(modelMapper.map(any(UnitSearch.class), any(Class.class))).thenReturn(entity);
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/units");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())));
    }

    @Test
    public void allShouldReturnEmptyList() throws Exception {
        Page page = Page.empty();
        when(service.findAll(any(), anyInt(), anyInt(), any())).thenReturn(page);

        RequestBuilder request = get("/units");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.data.list", empty()));
    }

    @Test
    public void getEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = get("/units/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }
    @Test
    public void deleteEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = delete("/units/2");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }

    @Test
    public void putEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/units/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"\",\"description\": \"\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.BAD_REQUEST.value())));

    }

    @Test
    public void putCodeEntityNotNUlLException() throws Exception{
        when(service.findById(any(Integer.class))).thenReturn(null);
        RequestBuilder request = put("/units/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"x\",\"description\": \"xas\"}");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code", is(HttpStatus.NOT_FOUND.value())));

    }


}
