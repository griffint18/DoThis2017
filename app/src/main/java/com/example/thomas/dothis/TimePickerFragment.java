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
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        super.onCreateDialog(savedInstanceState);

        int hour = getArguments().getInt("hour");
        int minute = getArguments().getInt("minute");
        year = getArguments().getInt("year");
        month = getArguments().getInt("month");
        day = getArguments().getInt("day");
        System.out.println("The hour is: " + hour + " the minute is: " + minute);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.out.println("Hour: " + hourOfDay + " Minutes: " + minute);
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hourOfDay, minute);

        doItEvent e = ((EditFieldClass) getActivity()).getEvent();

        e.setStartDT(gc);
    }
}
