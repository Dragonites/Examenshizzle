package com.dragonites.practice.hard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dragonites on 13/11/2016.
 */
public class MySimpleArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> candidates;
    private final List<String> parties;

    MySimpleArrayAdapter(Context context, List<String> candidates, List<String> parties) {
        super(context, -1, candidates);
        this.context = context;
        this.candidates = candidates;
        this.parties = parties;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView textView = (TextView) rowView.findViewById(R.id.name);

        textView.setText(candidates.get(position));

        switch (parties.get(position)) {
            case "Republican Party":
                imageView.setImageResource(R.drawable.republican_party);
                break;
            case "Democratic Party":
                imageView.setImageResource(R.drawable.democratic_party);
                break;
            default:
                imageView.setImageResource(R.drawable.usa_disc);
        }

        return rowView;
    }
}
