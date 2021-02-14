package com.enigma.api.inventory.controllers;

import com.enigma.api.inventory.entities.*;
import com.enigma.api.inventory.exceptions.EntityNotFoundException;
import com.enigma.api.inventory.models.*;
import com.enigma.api.inventory.services.CustomerService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import com.enigma.api.inventory.services.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.enigma.api.inventory.models.ResponseMessage.success;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StockService stockService;

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
        Stock stock = stockService.findById(item.getId());
        stock.setQuantity(stock.getQuantity()-1);
        stockService.save(stock);
        entity.setStockId(stock);
        entity.setItemId(stock.getItem());

        entity = transactionService.save(entity);
        TransactionResponse data = modelMapper.map(entity, TransactionResponse.class);
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
        Stock stock = stockService.findById(entity.getStockId().getId());
        stock.setQuantity(stock.getQuantity()+1);
        stockService.save(stock);
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
