package com.example.orvilleclarke.simpletodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    ReadAndWrite readAndWrite;

  // Intent intent = new Intent(MainActivity.this, EditOrDeleteActivity.class);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ties variable lvItems to screen view id
        lvItems = (ListView) findViewById(R.id.lvItems);


        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        ReadAndWrite RaW = new ReadAndWrite(filesDir, todoFile);
        readAndWrite = RaW;

        // reads items from file into arrayList<String> items
        //readItems();
        items = readAndWrite.readItems();

        // configures arrayAdapter
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        // configures lvItems with arrayAdapter
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();

    }

    // Listens for long click on list items and deletes them from list and notifies adapter to update
    // data set displayed on the screen
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                        Bundle b = new Bundle();

                        try {
                            b.putInt("key", pos);
                            //items = readAndWrite.readItems();
                            // Sends user to the EditOrDeleteActivity with a bundle that includes information on what entry to edit
                            Intent intent = new Intent(MainActivity.this, EditOrDeleteActivity.class);
                            intent.putExtras(b);
                            startActivity(intent);

                            finish();
                        } catch (Exception e) {

                        }



                        return true;
                    }


        });

    }



    // Button method adds new item to list array and writes to file
    public void onAddItem(View view) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        readAndWrite.writeItems(items);

    }


}
