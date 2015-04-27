package com.ubaier.shopinlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private EditText newItem;
    private ListView lv_itemList;
    private ShoppingItemAdapter arrayAdapter_itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
        setupViews();
    }

    private void setupViews() {

        newItem = (EditText) findViewById(R.id.et_newItem);
        lv_itemList = (ListView) findViewById(R.id.lv_listitems);

        // Update UI

        updateUI();

    }


    /*
    *  This method is linked to onClick even of add button for a new item
     */
    public void onAddItem(View v) {
        //Log.d("On Add item:", "on Add Item button was pressed!! The new value is: " + newItem.getText());

        // check if the edit text is empty
        if(!(newItem.getText().toString().equals("")) ) {
            ListItem newListItem = new ListItem(newItem.getText().toString());

            ShopinlistApplication app = (ShopinlistApplication) getApplication();

            app.addItem(newListItem);

            // Clear the new item field
            newItem.setText("");

            updateUI();
        }
        else
        {
            // Let user know that no was added.
            Toast.makeText(this, "No item added", Toast.LENGTH_LONG).show();
        }

    }


    /*
    *  This method is linked to onClick even of delete button for each item
     */
    public void onDelitem(View v){
        ImageButton imageButton = (ImageButton) v ;

        String item_id = imageButton.getTag().toString();
        //Log.d("Listener button", "that tag for id is :" + item_id);


        // update database
        ShopinlistApplication app = (ShopinlistApplication) getApplication();

        app.removeItem(Long.parseLong(item_id));

        // update UI
        updateUI();
    }


    /*
    *  This method is called to update the user interface.
     */
    public void updateUI(){
        ShopinlistApplication app = (ShopinlistApplication) getApplication();

        ArrayList<ListItem> listItems = app.getAllListItems();

        arrayAdapter_itemList = new ShoppingItemAdapter(this, R.layout.list_item, listItems);

        lv_itemList.setAdapter(arrayAdapter_itemList);

    }

    private class ShoppingItemAdapter extends ArrayAdapter<ListItem> {
        /**
         *  This is a custom listener for the List-view
         */
        private ArrayList<ListItem> listItemViews;

        public ShoppingItemAdapter(Context context, int textViewResourceId, ArrayList<ListItem> listItemViews) {
            super(context, textViewResourceId, listItemViews);
            this.listItemViews = new ArrayList<>();
            this.listItemViews.addAll(listItemViews);
        }

        private class ViewHolder {
            CheckBox name;
            ImageButton btn_del;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.list_item, null);

                holder = new ViewHolder();
                holder.btn_del = (ImageButton) convertView.findViewById(R.id.btn_del);
                holder.name = (CheckBox) convertView.findViewById(R.id.item_checkBox);
                convertView.setTag(holder);

                holder.name.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v ;

                        ListItem item = (ListItem) cb.getTag();

                        // get id from the Tag
                        long _id = ((ListItem) cb.getTag()).getId();

                        item.setId(_id);
                        item.setIsDone(cb.isChecked());

                        //update database
                        ShopinlistApplication app = (ShopinlistApplication) getApplication();
                        app.updateItem(item);
                    }
                });
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListItem item = listItemViews.get(position);

            holder.name.setTag(item.getId());
            holder.name.setText(item.getName());
            holder.name.setChecked(item.getIsDone());

            // Information about the item id is added in the tags. this can be used to update or delete the items
            holder.btn_del.setTag(item.getId());
            holder.name.setTag(item);
            return convertView;

        }

    }
}

