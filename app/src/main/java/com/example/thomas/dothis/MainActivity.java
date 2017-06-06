package com.example.thomas.dothis;
// https://www.youtube.com/watch?v=duHKgfl21BU
// 4:11
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.DatePickerDialog;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<doItEvent> arrayList;
    //ArrayList<doItEvent> arrayList;
    CustomAdapter arrayAdapter;

    SharedPreferences mPrefs;
    //String mString = mPrefs.getString("tag", "default_value_if_variable_not_found");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();

        mPrefs = getSharedPreferences("DoThis", MODE_PRIVATE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditClass.class);
                intent.putExtra(Intent_Constants.INTENT_TITLE_DATA, arrayList.get(position));
                intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
                startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_EDIT_EXISTING);
            }
        });

//        int count = mPrefs.getInt("count", 0);
//        for (Integer i = 1; i <= count; i++) {
//            arrayList.add(mPrefs.getString(i.toString(), ""));
//        }
        arrayAdapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

//    @Override
//    public void onBackPressed() {
//        try {
//            PrintWriter pw = new PrintWriter(openFileOutput("Todo.txt", Context.MODE_PRIVATE));
//            for(String data : arrayList) {
//                pw.println(data);
//            }
//            pw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        finish();
//    }

    @Override
    protected void onStop() {
        // Store current list
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.clear();
        mEditor.putInt("count", arrayList.size());
        for (Integer i = 1; i <= arrayList.size(); i++) {
            //mEditor.putString(i.toString(), arrayList.get(i-1)).commit();
        }

        super.onStop();
    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("in onStart, arrayList size is " + arrayList.size());
        // Retrieve the stored list
        arrayList.clear();
    }
*/
    public void onClickAddNew(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, EditFieldClass.class);
        doItEvent event = new doItEvent();
        intent.putExtra("NewEvent", event);
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_ADD_NEW);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String titleText;
        String locationText;
        int day = 0;
        int month = 0;
        int year = 0;
        if(resultCode == Intent_Constants.INTENT_REQUEST_ADD_NEW) {
            titleText = data.getStringExtra(Intent_Constants.INTENT_TITLE_FIELD);
            locationText = data.getStringExtra(Intent_Constants.INTENT_LOCATION_FIELD);
            System.out.println("Location text = " + locationText);
            doItEvent event = new doItEvent();
            event.setTitle(titleText);
            event.setLocation(locationText);
            data.getIntExtra(Intent_Constants.INTENT_DATE_DAY, day);
            data.getIntExtra(Intent_Constants.INTENT_DATE_MONTH, month);
            data.getIntExtra(Intent_Constants.INTENT_DATE_YEAR, year);

            event.setStartDT(new GregorianCalendar(year, month, day));

            System.out.println("before add, arraylist size is " + arrayList.size());
            System.out.println("before add, arrayAdapter size is " + arrayAdapter.getCount());
            //arrayList.add(titleText);
            //arrayList.add(locationText);

            arrayList.add(event);

            System.out.println("after add, arraylist size is " + arrayList.size());
            System.out.println("after add, arrayAdapter size is " + arrayAdapter.getCount());
            if (arrayAdapter == null) {
                System.out.println("arrayAdapter is nothing");
            } else {
                arrayAdapter.notifyDataSetChanged();
            }
            System.out.println("done");
        }

        else if (resultCode == Intent_Constants.INTENT_REQUEST_EDIT_EXISTING){
            titleText = data.getStringExtra(Intent_Constants.INTENT_CHANGED_TITLE);
            int position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
            if (position != -1) {
                //arrayList.remove(position);
                //arrayList.add(position, titleText);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
