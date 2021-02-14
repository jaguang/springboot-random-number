package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.Customer;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.CustomerModel;
import com.enigma.api.inventory.models.CustomerSearch;
import com.enigma.api.inventory.models.PagedList;
import com.enigma.api.inventory.models.ResponseMessage;
import com.enigma.api.inventory.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/{id}")
    public ResponseMessage<CustomerModel> edit(@PathVariable Integer id, @RequestBody @Valid CustomerModel model) {

        Customer entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        modelMapper.map(model, entity);
        service.save(entity);

        CustomerModel data = modelMapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<CustomerModel> removeById(@PathVariable Integer id) {
        Customer entity = service.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        CustomerModel data = modelMapper.map(entity, CustomerModel.class);

        return ResponseMessage.success(data);
    }

    @PostMapping
    public ResponseMessage<CustomerModel> add(@RequestBody @Valid CustomerModel model) {
        Customer entity = modelMapper.map(model, Customer.class);
        entity = service.save(entity);
        CustomerModel data = modelMapper.map(entity, CustomerModel.class);
        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<CustomerModel> findById(@PathVariable Integer id) {
        Customer entity = service.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        CustomerModel data = modelMapper.map(entity, CustomerModel.class);

        return ResponseMessage.success(data);
    }


    @GetMapping
    public ResponseMessage<PagedList<CustomerModel>> findAll(
            @Valid CustomerSearch model
    ) {
        Customer search = modelMapper.map(model, Customer.class);

        Page<Customer> entityPage = service.findAll(search, model.getPage(), model.getSize(), model.getSort());

        List<Customer> entities = entityPage.toList();

        List<CustomerModel> models = entities.stream()
                .map(e -> modelMapper.map(e, CustomerModel.class))
                .collect(Collectors.toList());

        PagedList<CustomerModel> data = new PagedList<>(models,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

}
