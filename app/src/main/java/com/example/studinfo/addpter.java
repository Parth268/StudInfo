package com.example.studinfo;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class addpter extends ArrayAdapter<attribute> {

    private Activity activity;
    private List<attribute> artList;

    public addpter( Activity activity, List<attribute> artList) {
        super(activity,R.layout.eventlist ,artList);
        this.activity = activity;
        this.artList = artList;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=activity.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.eventlist,null,true);
        TextView textView =(TextView)listViewItem.findViewById(R.id.collegename);
        TextView textView1=(TextView)listViewItem.findViewById(R.id.eventname);
        TextView textView2 =(TextView)listViewItem.findViewById(R.id.Dateofevent);
        TextView textView3 =(TextView)listViewItem.findViewById(R.id.cityofevent);
        attribute event= artList.get(position);
        textView.setText(event.getCollege());
        textView1.setText(event.getEvent());
        textView2.setText(event.getDate());
        textView3.setText(event.getCity());
        return listViewItem;


    }

}
