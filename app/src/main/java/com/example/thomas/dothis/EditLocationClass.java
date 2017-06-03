package com.example.thomas.dothis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
/**
 * Created by Thomas on 6/3/2017.
 */

public class EditLocationClass extends AppCompatActivity {
    String locationText;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("In the EditLocationClass");
        setContentView(R.layout.do_this_layout);
        Intent intent = getIntent();
        locationText = intent.getStringExtra(Intent_Constants.INTENT_LOCATION_DATA);
        position = intent.getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);
        EditText locationData = (EditText) findViewById(R.id.location);
        locationData.setText(locationText);
    }

    public void saveButtonClicked(View v) {
        System.out.println("In the EditLocationClass");
        String changedLocationText = ((EditText) findViewById(R.id.location)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.INTENT_CHANGED_LOCATION, changedLocationText);
        intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, position);
        setResult(Intent_Constants.INTENT_REQUEST_EDIT_EXISTING, intent);
        finish();
    }
}
