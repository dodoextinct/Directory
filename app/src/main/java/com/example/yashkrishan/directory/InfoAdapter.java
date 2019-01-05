package com.example.yashkrishan.directory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//Adapter class for list 
public class InfoAdapter extends ArrayAdapter {

    public InfoAdapter(Context context, ArrayList<Person> person) {

        super(context, 0, person);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Person person = new Person();

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.info, parent, false);}
            TextView name = (TextView)convertView.findViewById(R.id.name1);
            name.setText(person.name);
            return convertView;
    }
}
