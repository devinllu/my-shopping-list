package com.dabance.myshoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dabance.myshoppinglist.com.dabance.myshoppinglist.model.Item;

public class EditItemActivity extends AppCompatActivity {

    private static final String ITEM_DATA= "com.dabance.myshoppinglist.EditItemActivity - item data";
    private String itemData;

    public static Intent makeIntent(Context context, String itemData){

        Intent intent = new Intent(context, EditItemActivity.class);
        intent.putExtra(ITEM_DATA, itemData);
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

        loadSaveButton();
        loadDeleteButton();

    }

    private void loadSaveButton() {

        Button button = findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveItemData()){

                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void loadDeleteButton() {

        Button button = findViewById(R.id.deleteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = EditItemActivity.this.getSharedPreferences(getResources().getString(R.string.sharedPref), Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(itemData).apply();

                Toast.makeText(EditItemActivity.this, "Successfully deleted item from grocery list!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();

            }
        });
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

    private boolean saveItemData(){

        EditText itemName = findViewById(R.id.itemInput);
        EditText itemQuantity = findViewById(R.id.quantityInput);

        if(itemName.getText().length() > 0){
            if(Integer.parseInt(itemQuantity.getText().toString()) > 0){

                Item item = new Item(itemName.getText().toString(), Integer.parseInt(itemQuantity.getText().toString()));

                SharedPreferences sharedPreferences = EditItemActivity.this.getSharedPreferences(getResources().getString(R.string.sharedPref), Context.MODE_PRIVATE);
                sharedPreferences.edit().remove(itemData).apply();
                sharedPreferences.edit().putInt(item.getItemData(), 0).apply();

                Toast.makeText(EditItemActivity.this, "Successfully added item from grocery list!", Toast.LENGTH_SHORT).show();
                return true;
            }
        } else {

            itemName.setError("ERROR: Invalid item name");

        }
        return false;
    }
}
