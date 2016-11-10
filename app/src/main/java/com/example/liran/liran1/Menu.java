package com.example.liran.liran1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by liran on 20/10/2016.
 */

public class Menu extends ListActivity {

    String classes[] = {"BodyDetection", "FaceDetection", "example2", "example3","example4","example5","example6"};


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id){
        super.onListItemClick(l, v, position, id);;
        String pos = classes[position];
        try{
            Class ourClass = Class.forName("com.example.liran.liran1."+pos);
            Intent ourIntenet = new Intent(Menu.this, ourClass);
            startActivity(ourIntenet);
        }catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
