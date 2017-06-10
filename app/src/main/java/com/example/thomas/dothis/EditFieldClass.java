package com.example.thomas.dothis;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class EditFieldClass extends AppCompatActivity {
    doItEvent event;
    int pos;
    public doItEvent getEvent() {
        return event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_this_layout);
        event = (doItEvent) getIntent().getSerializableExtra("NewEvent");

        EditText titleData = (EditText) findViewById(R.id.title);
        titleData.setText(event.getTitle());

        EditText locationData = (EditText) findViewById(R.id.location);
        locationData.setText(event.getLocation());

        pos = getIntent().getIntExtra(Intent_Constants.INTENT_ITEM_POSITION, -1);

    }

    public void saveButtonClicked(View v) {
        String titleText = ((EditText) findViewById(R.id.title)).getText().toString();
        String locationText = ((EditText) findViewById(R.id.location)).getText().toString();
        if (titleText.equals("") || locationText.equals("")) {

        } else{
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.INTENT_TITLE_FIELD, titleText);
            intent.putExtra(Intent_Constants.INTENT_LOCATION_FIELD, locationText);
            int day = event.getStartDT().get(Calendar.DAY_OF_MONTH);
            intent.putExtra(Intent_Constants.INTENT_DATE_DAY, day);
            intent.putExtra(Intent_Constants.INTENT_DATE_MONTH, event.getStartDT().get(Calendar.MONTH));
            intent.putExtra(Intent_Constants.INTENT_DATE_YEAR, event.getStartDT().get(Calendar.YEAR));
            intent.putExtra(Intent_Constants.INTENT_TIME_HOUR, event.getStartDT().get(Calendar.HOUR_OF_DAY));
            intent.putExtra(Intent_Constants.INTENT_TIME_MINUTE, event.getStartDT().get(Calendar.MINUTE));
            intent.putExtra(Intent_Constants.INTENT_ITEM_POSITION, pos);
            setResult(Intent_Constants.INTENT_RESULT_CODE, intent);
            finish();
        }
    }

    public void showDatePicker(View v) {
        DialogFragment fragment = new DatePickerFragment();
        Bundle bundle = dateTimeData();
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePicker(View v) {
        DialogFragment fragment = new TimePickerFragment();
        Bundle bundle = dateTimeData();
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle dateTimeData(){
        Bundle bundle = new Bundle();
        bundle.putInt("year", event.getStartDT().get(Calendar.YEAR));
        bundle.putInt("month", event.getStartDT().get(Calendar.MONTH));
        bundle.putInt("day", event.getStartDT().get(Calendar.DAY_OF_MONTH));
        bundle.putInt("hour", event.getStartDT().get(Calendar.HOUR_OF_DAY));
        bundle.putInt("minute", event.getStartDT().get(Calendar.MINUTE));
        return bundle;
    }
}
