package com.ubaier.shopinlist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText newItem;
    private Button btn_add;
    private Button btn_done;
    private Button btn_del;
    private ListView lv_itemList;
    private TextView tv_item;
    private ArrayAdapter<String> arrayAdapter_itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );

        setupViews();
    }

    private void setupViews() {

        newItem = (EditText) findViewById(R.id.et_newItem);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_done = (Button) findViewById(R.id.btn_done);
        btn_del = (Button) findViewById(R.id.btn_del);
        lv_itemList = (ListView) findViewById(R.id.lv_listitems);


        updateUI();

    }


    public void onAddItem(View v) {
        Log.d("On Add item:", "on Add Item button was pressed!! The new value is: " + newItem.getText());

        // check if the edit text is empty
        if(!(newItem.getText().toString().equals("")) ) {
            ListItem newListItem = new ListItem(newItem.getText().toString());

            ShopinlistApplication app = (ShopinlistApplication) getApplication();

            app.addItem(newListItem);

            // Clear the new item field
            newItem.setText("");

            updateUI();
        }

    }


    public void updateUI(){
        ShopinlistApplication app = (ShopinlistApplication) getApplication();

        ArrayList<ListItem> listItems = app.getAllListItems();

        arrayAdapter_itemList = new ArrayAdapter<>(this, R.layout.list_item, android.R.id.text1);

        for (ListItem item : listItems) {
            arrayAdapter_itemList.add(item.getName());
        }

        lv_itemList.setAdapter(arrayAdapter_itemList);

    }

}
