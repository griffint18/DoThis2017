package com.example.thomas.dothis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.GregorianCalendar;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<doItEvent> arrayList;
    CustomAdapter arrayAdapter;
    SharedPreferences mPrefs;

    // Execute this code upon startup
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        arrayList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, EditFieldClass.class);
                doItEvent event = arrayList.get(position);
                if (event != null) {
                    System.out.println("Event not null");
                    intent.putExtra("NewEvent", event);
                    System.out.println("Event was serialized");
                    intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
                    System.out.println("Intent was edited");
                    startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_EDIT_EXISTING);
                }
            }


        });
        // Remove item from list upon long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return true;
            }
        });

        // Create the sharedPreferences to store the data
        mPrefs = getSharedPreferences("DoThis", MODE_PRIVATE);
        int count = mPrefs.getInt("count", 0);
        for (Integer i = 1; i <= count; i++) {
            doItEvent e = new doItEvent();
            e.readData(mPrefs, i.toString());
            arrayList.add(e);
        }

        // Link the listView to the custom adapter
        arrayAdapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    // Save the data upon stoppage of the app
    @Override
    protected void onStop() {
        // Store current list
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.clear();
        mEditor.putInt("count", arrayList.size()).apply();

        for (Integer i = 1; i <= arrayList.size(); i++) {
            doItEvent e = arrayList.get(i-1);
            e.saveData(mPrefs, i.toString());
        }
        super.onStop();
    }
    // Executes when the "+" button is pressed on the main screen
    public void onClickAddNew(View v) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, EditFieldClass.class);
        doItEvent event = new doItEvent();
        intent.putExtra("NewEvent", event);
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        startActivityForResult(intent, Intent_Constants.INTENT_REQUEST_ADD_NEW);
    }

    // Save is pressed, return here and execute the code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        doItEvent event = null;

        if (resultCode == Intent_Constants.INTENT_RESULT_CODE) {
            if(requestCode == Intent_Constants.INTENT_REQUEST_ADD_NEW) {
                event = new doItEvent();
            }

            else if (requestCode == Intent_Constants.INTENT_REQUEST_EDIT_EXISTING){
                int position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
                if (position != -1) {
                    event = arrayList.get(position);
                }
            }
        }

        if (event != null) {
            updateEventPostEdit(event, data, requestCode);
        }
    }

    // Update the event after editing the existing one
    private void updateEventPostEdit(doItEvent e, Intent data, int requestCode) {
        String titleText = data.getStringExtra(Intent_Constants.INTENT_TITLE_FIELD);
        String locationText = data.getStringExtra(Intent_Constants.INTENT_LOCATION_FIELD);

        int day = data.getIntExtra(Intent_Constants.INTENT_DATE_DAY, 0);
        int month = data.getIntExtra(Intent_Constants.INTENT_DATE_MONTH, 0);
        int year = data.getIntExtra(Intent_Constants.INTENT_DATE_YEAR, 0);
        int hour = data.getIntExtra(Intent_Constants.INTENT_TIME_HOUR, 0);
        int minute = data.getIntExtra(Intent_Constants.INTENT_TIME_MINUTE, 0);
        e.setStartDT(new GregorianCalendar(year, month, day, hour, minute));
        e.setTitle(titleText);
        e.setLocation(locationText);

        if(requestCode == Intent_Constants.INTENT_REQUEST_ADD_NEW) {
            arrayList.add(e);
        } else if (requestCode == Intent_Constants.INTENT_REQUEST_EDIT_EXISTING) {
            int position = data.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
            if (position != -1) {
                arrayList.remove(position);
                arrayList.add(position, e);
            }
        }

        if (arrayAdapter == null) {

        } else {
            //Refresh the view
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
