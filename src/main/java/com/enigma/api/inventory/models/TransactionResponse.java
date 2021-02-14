package com.enigma.api.inventory.models;


public class TransactionResponse {

    private Integer id;

    private String name;

    private ItemElement itemId;

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

    public ItemElement getItemId() {
        return itemId;
    }

    public void setItemId(ItemElement itemId) {
        this.itemId = itemId;
    }

    }
