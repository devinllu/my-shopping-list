package com.dabance.myshoppinglist.com.dabance.myshoppinglist.model;

import androidx.annotation.NonNull;

public class Item {

    private String name;
    private int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemData(){
        return name + ", " + quantity;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " x " + quantity;
    }
}
