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
    GregorianCalendar event;
    Calendar c;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        c = Calendar.getInstance();

        int hour = c.get(event.HOUR);
        int minute = c.get(event.MINUTE);

        System.out.println("The hour is: " + hour + " the minute is: " + minute);

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.out.println("Hour: " + hourOfDay + " Minutes: " + minute);
    }
}
