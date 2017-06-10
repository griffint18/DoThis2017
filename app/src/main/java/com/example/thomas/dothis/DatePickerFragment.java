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
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        super.onCreateDialog(savedInstanceState);

        System.out.println("In the onCreate");
        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        hour = getArguments().getInt("hour");
        minute = getArguments().getInt("minute");
        System.out.println("The year is: " + year + " the month is: " + month + " the day is: " + day);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        System.out.println("Day: " + day + " Month: " + month + " Year: " + year);
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, minute);

        doItEvent e = ((EditFieldClass) getActivity()).getEvent();

        e.setStartDT(gc);
    }
}
