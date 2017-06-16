package com.example.thomas.dothis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Thomas on 6/5/2017.
 */

public class CustomAdapter extends ArrayAdapter<doItEvent> {

    ViewHolder viewHolder = new ViewHolder();

    // Default constructor
    private static class ViewHolder {
        private LinearLayout linearLayout;
    }

    // New custom adapter to link with arrayList
    public CustomAdapter(Context context, int textViewResourceId, ArrayList<doItEvent> items) {
        super(context, textViewResourceId, items);
    }

    // Define the view as getView and present the list
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.event_layout, parent, false);


            viewHolder.linearLayout = (LinearLayout) convertView.findViewById(R.id.eventLayout);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        doItEvent item = getItem(position);
        if (item!= null) {
            TextView t = (TextView) convertView.findViewById(R.id.eventTitle);
            TextView l = (TextView) convertView.findViewById(R.id.eventLocation);
            TextView dt = (TextView) convertView.findViewById(R.id.eventDateTime);
            if (t != null) {
                t.setText(item.getTitle());
            }
            if (l != null) {
                l.setText(item.getLocation());
            }
            if (dt != null) {
                SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy h:mm a");
                Date d = item.getStartDT().getTime();
                String dateString = fmt.format(d);
                dt.setText(dateString);
            }
        }
        return convertView;
    }
}
