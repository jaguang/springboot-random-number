package com.enigma.api.inventory.models;

public class StockSearch extends PageSearch {

    private String quantity;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
