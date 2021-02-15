package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Quantity;
import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.QuantitySummary;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.services.QuantityService;
import com.enigma.api.inventory.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/quantity")
@RestController
public class QuantityController {

    @Autowired
    private QuantityService service;

    @Autowired
    private ItemService itemService;

    @Autowired
    private QuantityService quantityService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseMessage<QuantityResponse> add(@RequestBody @Valid QuantityRequest model) {
        Quantity entity = modelMapper.map(model, Quantity.class);

        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);
        entity.setTotalPrice(entity.getQuantity() * item.getPrice());
        entity = service.save(entity);
        QuantityResponse data = modelMapper.map(entity, QuantityResponse.class);
        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<QuantityResponse> edit(@PathVariable Integer id, @RequestBody @Valid QuantityRequest model) {
        Quantity entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        Item item = itemService.findById(model.getItemId());
        entity.setItem(item);

        modelMapper.map(model, entity);

        entity.setTotalPrice(entity.getQuantity() * item.getPrice());
        service.save(entity);

        QuantityResponse data = modelMapper.map(entity, QuantityResponse.class);

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<QuantityResponse> removeById(@PathVariable Integer id) {
        Quantity entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        QuantityResponse data = modelMapper.map(entity, QuantityResponse.class);

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<QuantityResponse> findById(@PathVariable Integer id) {
        Quantity entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        QuantityResponse data = modelMapper.map(entity, QuantityResponse.class);

        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<QuantityResponse>> findAll(
            @Valid QuantitySearch model
    ) {
        Quantity search = modelMapper.map(model, Quantity.class);

        Page<Quantity> entityPage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());

        List<Quantity> entities = entityPage.toList();

        List<QuantityResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, QuantityResponse.class))
                .collect(Collectors.toList());

        PagedList<QuantityResponse> data = new PagedList<>(models,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @GetMapping("/summaries")
    public ResponseMessage<List<QuantitySummary>> findAllSummaries(){
        List<QuantitySummary> entityPage = service.findAllSummaries();
        return ResponseMessage.success(entityPage);
    }

}
