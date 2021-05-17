package com.example.akdenizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends ArrayAdapter<EventModel> {
    private static final String a= "EventListAdapter";
    private Context mContext;
    int mResource;

    public EventListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<EventModel> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String date = getItem(position).getDate();
        String title = getItem(position).getTitle();

        EventModel event =new EventModel(date, title);
        LayoutInflater inflater =LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);

        TextView tvDate = (TextView) convertView.findViewById(R.id.textEventDate);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.textEventTitle);
        tvDate.setText(date);
        tvTitle.setText(title);
        return convertView;

    }
}
