package com.example.thomas.dothis;

import android.content.Intent;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/3/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    GregorianCalendar event;
    Calendar c;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker

        Calendar c = Calendar.getInstance();

        System.out.println("In the onCreate");

        event = (GregorianCalendar) getActivity().getIntent().getSerializableExtra("EventDt");

        System.out.println("Got the event");

        int year = c.get(event.YEAR);
        int month = c.get(event.MONTH);
        int day = c.get(event.DAY_OF_MONTH);

        //event.getStartDT();
        System.out.println("The year is: " + year + " the month is: " + month + " the day is: " + day);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        System.out.println("Day: " + day + " Month: " + month + " Year: " + year);
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        EditFieldClass c = (EditFieldClass) getActivity();
        c.setStartDT(gc);
    }
}
