package com.dragonites.practice.simple;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Dragonites on 13/11/2016.
 */

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_fragment);

        String[] candidates = getResources().getStringArray(R.array.names);
        String[] parties = getResources().getStringArray(R.array.parties);

        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getActivity(), candidates, parties);
        listView.setAdapter(customAdapter);

        return view;
    }
}
