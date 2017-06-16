package com.example.thomas.dothis;

import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/7/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private int year;
    private int month;
    private int day;

    // Code executed when button to select time is pressed
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default time in the picker
        super.onCreateDialog(savedInstanceState);

        int hour = getArguments().getInt("hour");
        int minute = getArguments().getInt("minute");
        year = getArguments().getInt("year");
        month = getArguments().getInt("month");
        day = getArguments().getInt("day");

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    // Performs the code when the time has been set
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hourOfDay, minute);
        doItEvent e = ((EditFieldClass) getActivity()).getEvent();
        e.setStartDT(gc);
    }
}
