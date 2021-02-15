package com.enigma.api.inventory.entities;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
public class Quantity extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price",nullable = true)
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(id, quantity.id) && Objects.equals(item, quantity.item) && Objects.equals(this.quantity, quantity.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, quantity);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", item=" + item +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
