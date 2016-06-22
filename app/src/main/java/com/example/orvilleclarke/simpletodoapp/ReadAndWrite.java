package com.example.orvilleclarke.simpletodoapp;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Orville Clarke on 6/21/2016.
 */
public class ReadAndWrite {


    ArrayList<String> items;
    File filesDir;
    File todoFile;


  public ReadAndWrite(File filesDir1, File todoFile1){

      filesDir = filesDir1;
      todoFile = todoFile1;
      items = new ArrayList<String>();

  }



    void writeItems(ArrayList<String> items) {

         todoFile = new File(filesDir, "todo.txt");
        try{
            FileUtils.writeLines(todoFile, items);
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    ArrayList<String> readItems(){

        try{
            items.clear();
            items = new ArrayList<String>(FileUtils.readLines(todoFile));

        }catch (IOException e)
        {
            items = new ArrayList<String>();
            e.printStackTrace();
        }

        return  items;
    }
}
