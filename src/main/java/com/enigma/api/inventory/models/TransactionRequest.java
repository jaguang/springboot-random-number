package com.enigma.api.inventory.models;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.entities.Stock;


public class TransactionRequest {

    private Integer id;

    private String name;

    private Integer ItemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer itemId) {
        ItemId = itemId;
    }
}
