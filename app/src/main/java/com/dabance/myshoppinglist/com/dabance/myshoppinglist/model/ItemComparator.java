package com.dabance.myshoppinglist.com.dabance.myshoppinglist.model;

import java.util.Comparator;

class nameComparator implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class quantityComparator implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        return Integer.toString(o1.getQuantity()).compareTo(Integer.toString(o2.getQuantity()));
    }
}
