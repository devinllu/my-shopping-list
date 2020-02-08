package com.dabance.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EditItemActivity extends AppCompatActivity {

    private static final String ITEM_DATA= "com.dabance.myshoppinglist.EditItemActivity - item data";
    private String itemData;

    public static Intent makeIntent(Context context, String lensData ){

        Intent intent = new Intent(context, EditItemActivity.class);
        intent.putExtra(ITEM_DATA, lensData);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        extractDataFromIntent();

        if(!itemData.equalsIgnoreCase("")){
            loadItemInfo();
        }
    }

    private void extractDataFromIntent() {

        Intent intent = getIntent();
        itemData = intent.getStringExtra(ITEM_DATA);

    }

    private void loadItemInfo(){

        TextView item = findViewById(R.id.itemInput);
        TextView quantity = findViewById(R.id.quantityInput);

        String[] data = itemData.split(",");

        item.setText(data[0]);
        quantity.setText(data[1]);

    }
}
