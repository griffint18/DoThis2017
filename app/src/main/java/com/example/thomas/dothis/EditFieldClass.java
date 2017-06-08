package com.example.thomas.dothis;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.GregorianCalendar;

public class EditFieldClass extends AppCompatActivity {
    doItEvent event;

    public void setStartDT(GregorianCalendar c) {
        event.setStartDT(c);
        System.out.println("The set date is: " + c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("in onCreate for EditFieldClass");
        setContentView(R.layout.do_this_layout);
        event = (doItEvent) getIntent().getSerializableExtra("NewEvent");

        System.out.println("Event location: " + event.getLocation());
        System.out.println("Event title: " + event.getTitle());
        System.out.println("Event time: " + event.getStartDT().toString());

        EditText titleData = (EditText) findViewById(R.id.title);
        titleData.setText(event.getTitle());

        EditText locationData = (EditText) findViewById(R.id.location);
        locationData.setText(event.getLocation());
    }

    public void saveButtonClicked(View v) {
        String titleText = ((EditText) findViewById(R.id.title)).getText().toString();
        String locationText = ((EditText) findViewById(R.id.location)).getText().toString();
        System.out.println("Date and time is: " + event.getStartDT());
        if (titleText.equals("") || locationText.equals("")) {

        } else{
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.INTENT_TITLE_FIELD, titleText);
            intent.putExtra(Intent_Constants.INTENT_LOCATION_FIELD, locationText);
            intent.putExtra(Intent_Constants.INTENT_DATE_DAY, event.getStartDT().DAY_OF_MONTH);
            intent.putExtra(Intent_Constants.INTENT_DATE_MONTH, event.getStartDT().MONTH);
            intent.putExtra(Intent_Constants.INTENT_DATE_YEAR, event.getStartDT().YEAR);
            setResult(Intent_Constants.INTENT_RESULT_CODE, intent);
            finish();
        }
    }

    public void showDatePicker(View v) {
        DialogFragment fragment = new DatePickerFragment();
        Intent intent = new Intent();
        intent.setClass(this, EditFieldClass.class);
        intent.putExtra("EventDT", event);
        fragment.show(getSupportFragmentManager(), "datePicker");
        System.out.println("In showDatePicker after shown fragment");
    }

    public void showTimePicker(View v) {
        DialogFragment fragment = new TimePickerFragment();
        Intent intent = new Intent();
        intent.setClass(this, EditFieldClass.class);
        intent.putExtra("EventT", event);
        fragment.show(getSupportFragmentManager(), "timePicker");
        System.out.println("In showTimePicker after shown fragment");
    }
}
