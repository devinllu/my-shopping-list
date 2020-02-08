package com.dabance.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dabance.myshoppinglist.com.dabance.myshoppinglist.model.Item;
import com.dabance.myshoppinglist.com.dabance.myshoppinglist.model.ItemManager;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_EDIT_ITEM = 0;
    private ItemManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = ItemManager.getInstance();

        loadDefaultItems();
        populateList();

    }

    private void loadDefaultItems() {

        manager.addItem(new Item("bob", 12));

    }

    public void populateList() {


        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, R.layout.items, manager.getList());

        ListView list = findViewById(R.id.listview);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = EditItemActivity.makeIntent(MainActivity.this, manager.getItem(position).getItemData());
                startActivity(intent);


            }
        });
    }
}
