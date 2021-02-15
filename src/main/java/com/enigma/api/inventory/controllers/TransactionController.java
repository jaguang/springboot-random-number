package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.*;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.services.CustomerService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.QuantityService;
import com.enigma.api.inventory.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private QuantityService quantityService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseMessage<TransactionResponse> add(@RequestBody @Valid TransactionRequest model) {
        Transaction entity = modelMapper.map(model, Transaction.class);

        Item item = itemService.findById(model.getItemId());
        Random random = new Random();
        List<Customer> customerList = customerService.findAll();
        Customer cus = customerList.get(random.nextInt(customerList.size()));
        entity.setCustomerId(cus);
        Quantity quantity = quantityService.findById(item.getId());
        System.out.println(quantity.getQuantity());
        if(quantity.getQuantity()<=0){
            return ResponseMessage.error(403,"Stock Empty");
        } else {
            quantity.setQuantity(quantity.getQuantity() - 1);
            quantityService.save(quantity);
        }
        entity.setStockId(quantity);
        entity.setItemId(quantity.getItem());

        entity = transactionService.save(entity);
        TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);
        data.setName(entity.getCustomerId().getName());
        return ResponseMessage.success(data);
    }

    @GetMapping
    public ResponseMessage<PagedList<TransactionResponse>> findAll(
            @Valid TransactionSearch model
    ) {
        Transaction search = modelMapper.map(model, Transaction.class);

        Page<Transaction> entityPage = transactionService.findAll(search, model.getPage(), model.getSize(), model.getSort());

        List<Transaction> entities = entityPage.toList();

        List<TransactionResponse> models = entities.stream()
                .map(e -> modelMapper.map(e, TransactionResponse.class))
                .collect(Collectors.toList());

        PagedList<TransactionResponse> data = new PagedList<>(models,
                entityPage.getNumber(),
                entityPage.getSize(),
                entityPage.getTotalElements());

        return ResponseMessage.success(data);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<TransactionResponse> removeById(@PathVariable Integer id) {
        Transaction entity = transactionService.removeById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        Quantity quantity = quantityService.findById(entity.getStockId().getId());
        quantity.setQuantity(quantity.getQuantity()+1);
        quantityService.save(quantity);
        ModelMapper modelMapper = new ModelMapper();
        TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);

        return ResponseMessage.success(data);
    }

    @GetMapping("/{id}")
    public ResponseMessage<TransactionResponse> findById(@PathVariable Integer id) {
        Transaction entity = transactionService.findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        ModelMapper modelMapper = new ModelMapper();
        TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);

        return ResponseMessage.success(data);
    }






}
