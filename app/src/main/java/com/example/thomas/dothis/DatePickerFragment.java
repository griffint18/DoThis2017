package com.example.thomas.dothis;

import android.content.Intent;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/3/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private int hour;
    private int minute;

    // Code executed when button to select date is pressed
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        super.onCreateDialog(savedInstanceState);

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        hour = getArguments().getInt("hour");
        minute = getArguments().getInt("minute");

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    // Performs the code when the date has been set
    public void onDateSet(DatePicker view, int year, int month, int day) {
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, minute);
        doItEvent e = ((EditFieldClass) getActivity()).getEvent();
        e.setStartDT(gc);
    }
}
