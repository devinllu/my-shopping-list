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



//    public void set(int index, Item item) {
//        itemList[index] = item;
//    }


    public void sortByQuantity() {

        for (int i = 0; i < itemList.size() - 1; i++) {
            for (int j = i + 1; j < itemList.size(); j++) {
                if (itemList.get(i).getQuantity() > itemList.get(j).getQuantity()) {
                    Item temp = itemList.get(i);
                    itemList.set(i, itemList.get(j));
                    itemList.set(j, temp);
                }
            }
        }

    }

    public void sortByName() {
        for (int i = 0; i < itemList.size() - 1; i++) {
            for (int j = i + 1; j < itemList.size(); j++) {
                if (itemList.get(i).getName().compareTo(itemList.get(j).getName()) > 0) {
                    Item temp = itemList.get(i);
                    itemList.set(i, itemList.get(j));
                    itemList.set(j, temp);
                }
            }
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return itemList.iterator();
    }



}
