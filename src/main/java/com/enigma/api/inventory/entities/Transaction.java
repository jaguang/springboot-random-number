package com.enigma.api.inventory.entities;

import javax.persistence.*;

@Table
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "costumer_id")
    private Customer customerId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item itemId;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Quantity quantityId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public Quantity getStockId() {
        return quantityId;
    }

    public void setStockId(Quantity quantityId) {
        this.quantityId = quantityId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", itemId=" + itemId +
                ", stockId=" + quantityId +
                '}';
    }
}
