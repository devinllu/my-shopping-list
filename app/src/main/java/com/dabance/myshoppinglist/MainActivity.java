package com.dabance.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dabance.myshoppinglist.com.dabance.myshoppinglist.model.Item;
import com.dabance.myshoppinglist.com.dabance.myshoppinglist.model.ItemManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int ADD_EDIT_ITEM = 0;
    private ItemManager itemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getResources().getString(R.string.sharedPref), Context.MODE_PRIVATE);
//        sharedPreferences.edit().clear().commit();

        itemManager = ItemManager.getInstance();

        sortByNameButton();
        sortByQuantityButton();

        loadItems();
        populateList();


        FloatingActionButton fab = findViewById(R.id.addFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = EditItemActivity.makeIntent(MainActivity.this, "");
                startActivityForResult(intent, ADD_EDIT_ITEM);

            }
        });
    }

    private void sortByQuantityButton() {
        final Button button = findViewById(R.id.sortQuantityButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemManager.sortByQuantity();

                populateList();
//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
            }
        });

    }

    private void sortByNameButton() {
        Button button = findViewById(R.id.sortNameButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemManager.sortByName();
                populateList();


//                finish();
//                overridePendingTransition(0, 0);
//                startActivity(getIntent());
//                overridePendingTransition(0, 0);
            }
        });


    }

    @Override
    protected void onStart(){
        super.onStart();

        //load everything from SharedPreference onto LensManager, this usually happens on startup or coming back from
        //another activity, LensManager list is used for displaying list of lenses and gets deleted and reloaded after
        loadItems();
        populateList();
    }


    @Override
    protected void onStop(){
        super.onStop();

        //When program closes, or switches activity, everything from LensManager is saved onto SharedPreference file
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.sharedPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for(int i = 0; i < itemManager.getList().size(); i++){
            editor.putInt(itemManager.getItem(i).getItemData(), 0);
            editor.apply();
        }
        itemManager.getList().clear();
    }

    private void loadItems() {

        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(getString(R.string.sharedPref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> itemList = sharedPreferences.getAll();

        //read the key's only
        for(String key : itemList.keySet()){
            extractItemInfo(key);
        }
        editor.clear().apply();
    }

    private void extractItemInfo(String itemData){

        String[] listValues = itemData.split(",");

        String name = listValues[0];
        int quantity = Integer.parseInt(listValues[1]);

        Item item = new Item(name, quantity);
        itemManager.addItem(item);
    }


    public void populateList() {

        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this, R.layout.items, itemManager.getList());

        ListView list = findViewById(R.id.listview);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = EditItemActivity.makeIntent(MainActivity.this, itemManager.getItem(position).getItemData());
                startActivityForResult(intent, ADD_EDIT_ITEM);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_EDIT_ITEM){
            if(resultCode == RESULT_OK){

                populateList();
            }
        }
    }
}
