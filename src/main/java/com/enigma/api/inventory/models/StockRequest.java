package com.enigma.api.inventory.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StockRequest {

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer itemId;

    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrie) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
