package com.dabance.myshoppinglist.com.dabance.myshoppinglist.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemManager implements Iterable<Item> {

    private static ItemManager instance;
    private List<Item> itemList = new ArrayList<>();

    private ItemManager() {

    }

    public static ItemManager getInstance() {
        if(instance == null) {
            instance = new ItemManager();
        }
        return instance;
    }


    public List<Item> getList() { return itemList;}

    public int getLength() {
        return itemList.size();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public Item getItem(int index) {
        return itemList.get(index);
    }

    @Override
    public Iterator<Item> iterator() {
        return itemList.iterator();
    }

    public void sortByQuantity() {

        lst.sort(new quantityComparator());

    }
    
    public void sortByName() {
        lst.sort(new nameComparator());
    }
}
