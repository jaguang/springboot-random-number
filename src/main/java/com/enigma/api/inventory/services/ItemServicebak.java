package com.enigma.api.inventory.services;

import com.enigma.api.inventory.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class ItemServicebak {

//    private final List<Item> items = new ArrayList<>();
//
//    @PostConstruct
//    protected void init() {
//        items.add(new Item(1, "A"));
//        items.add(new Item(2, "B"));
//        items.add(new Item(3, "C"));
//        items.add(new Item(4, "D"));
//        items.add(new Item(5, "E"));
//    }
//
//    public Item edit(Item item) {
//        for (Item i : items) {
//            if (i.getId().equals(item.getId())) {
//                i.setName(item.getName());
//                return i;
//            }
//        }
//        return null;
//    }
//
//    public Item removeById(Integer id) {
//        // untuk menghapus list harus menggunakna iterator tidak bisa menggunakan looping
//        Iterator<Item> itemsIterator = items.iterator();
//        while (itemsIterator.hasNext()) {
//            Item item = itemsIterator.next();
//            if (item.getId().equals(id)) {
//                itemsIterator.remove();
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public Item add(Item item) {
//        items.add(item);
//        return item;
//    }
//
//    public Item findById(Integer id) {
//        for (Item item : items) {
//            if (item.getId() == id) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public List<Item> findAll() {
//        return items;
//    }
}
