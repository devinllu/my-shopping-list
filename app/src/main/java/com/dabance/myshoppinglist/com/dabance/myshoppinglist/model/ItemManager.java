package com.dabance.myshoppinglist.com.dabance.myshoppinglist.model;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private List<Item> lst = new ArrayList<>();

    public ItemManager(List<Item> lst) {
        lst.add(new Item("eggs", 1));
        lst.add(new Item("bread", 1));
        lst.add(new Item("bananas", 1));
        lst.add(new Item("apples", 1));
        lst.add(new Item("muffins", 1));
        lst.add(new Item("milk", 1));
        lst.add(new Item("cereal", 1));
        lst.add(new Item("cheese", 1));
        lst.add(new Item("chicken breast", 1));
    }

    public int getLength() {
        return lst.size();
    }

    public void addItem(Item item) {
        lst.add(item);
    }

    public Item getItem(int index) {
        return lst.get(index);
    }
}
