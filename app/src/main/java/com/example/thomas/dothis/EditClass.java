package com.example.thomas.dothis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Thomas on 6/3/2017.
 */

public class EditClass extends AppCompatActivity {
    String titleText;
    String locationText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In the EditClass");
        setContentView(R.layout.do_this_layout);
        Intent intent = getIntent();

        titleText = intent.getStringExtra(Intent_Constants.INTENT_TITLE_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText titleData = (EditText) findViewById(R.id.title);
        titleData.setText(titleText);

        locationText = intent.getStringExtra(Intent_Constants.INTENT_LOCATION_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText locationData = (EditText) findViewById(R.id.location);
        locationData.setText(locationText);

    }

    public void saveButtonClicked(View v) {
        //System.out.println("Location class debug");

        String changedTitleText = ((EditText) findViewById(R.id.title)).getText().toString();
        String changedLocationText = ((EditText) findViewById(R.id.location)).getText().toString();
        System.out.println("The title is: " + changedTitleText + ", the location is: " + changedLocationText);
        Intent intent = new Intent();

        //save title
        intent.putExtra(Intent_Constants.INTENT_CHANGED_TITLE, changedTitleText);

        //save location
        intent.putExtra(Intent_Constants.INTENT_CHANGED_LOCATION, changedLocationText);

        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_REQUEST_EDIT_EXISTING, intent);
        finish();
    }

}
