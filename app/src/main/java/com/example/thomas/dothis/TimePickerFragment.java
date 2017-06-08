package com.example.thomas.dothis;

import android.app.TimePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;
import android.text.format.DateFormat;

import java.util.Calendar;
/**
 * Created by Thomas on 6/7/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        Calendar c = Calendar.getInstance();

        int hour = args.getInt("hour", c.get(Calendar.HOUR_OF_DAY));
        int minute = args.getInt("minute", c.get(Calendar.MINUTE));

        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.out.println("Hour: " + hourOfDay + " Minutes: " + minute);
    }
}
