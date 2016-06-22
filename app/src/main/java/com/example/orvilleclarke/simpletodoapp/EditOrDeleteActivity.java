package com.example.orvilleclarke.simpletodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class EditOrDeleteActivity extends AppCompatActivity {


    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    TextView tvItem;
    EditText etUpdateText ;
    ReadAndWrite raw;

    int Value = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set content
        setContentView(R.layout.activity_edit_or_delete);

        // links textview to tvItem
         tvItem = (TextView) findViewById(R.id.tvItem);

        // links edittext to etUpdateText
        etUpdateText = (EditText) findViewById(R.id.etUpdateText);

        // gets messages from intent
        Bundle b = getIntent().getExtras();

        if(b != null){
            Value = b.getInt("key");
            File filesDir = getFilesDir();

            File todoFile = new File (filesDir, "todo.txt" );
            ReadAndWrite RaW = new ReadAndWrite(filesDir, todoFile);
            raw = RaW;
            items =  raw.readItems();

            String tempor = items.get(Value);
            items = raw.readItems();
            raw.writeItems(items);
            tvItem.setText(tempor);
        }
    }




    // onUpdate edits an entry on the simpleToDo list
    public void onUpdate(View view){


        // grabs string from the editText etUpdateText and saves to string
        String fieldValue = etUpdateText.getText().toString();

        if(fieldValue.isEmpty()){


        }else{

            // uses Value as index in items and replaces the list entry with fieldValue
            items.set(Value, fieldValue);
            // Saves updated arrayList to file
            raw.writeItems(items);
        }




        // Sends user back to MainActivity
        Intent intent = new Intent(EditOrDeleteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();



    }


    //  Sends user back to MainActivity without any changes to the SimpleToDo List
    public void onCancel(View view){

        // Creates  an intent to the MainActivity
        Intent intent = new Intent(EditOrDeleteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    // Deletes the entry on the simpleToDo List
    public void onDelete (View view){

        // removes entire entry from the ArrayList
        items.remove(Value);

        //  Updates file with newly augmented arrayList
        raw.writeItems(items);

        // Sends user back to the MainActivity
        Intent intent = new Intent(EditOrDeleteActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
