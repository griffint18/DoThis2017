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

import java.util.ArrayList;

/**
 * Created by Thomas on 6/5/2017.
 */

public class CustomAdapter extends ArrayAdapter<doItEvent> {

    ViewHolder viewHolder = new ViewHolder();

    private static class ViewHolder {
        private LinearLayout linearLayout;
    }

    public CustomAdapter(Context context, int textViewResourceId, ArrayList<doItEvent> items) {
        super(context, textViewResourceId, items);
    }

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
            // do whatever you want with your string and long
//            TextView t = (TextView) viewHolder.linearLayout.findViewById(R.id.eventTitle);
//            t.setText(item.getTitle());
//            t = (TextView) viewHolder.linearLayout.findViewById(R.id.eventLocation);
//            t.setText(item.getLocation());
            System.out.println("About to get to convertView");
            TextView t = (TextView) convertView.findViewById(R.id.eventTitle);
            TextView l = (TextView) convertView.findViewById(R.id.eventLocation);

            if (t != null) {
                t.setText(item.getTitle());
            }
            if (l != null) {
                l.setText(item.getLocation());
            }
        }
        return convertView;
    }
}
