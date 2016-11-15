package com.dragonites.practice.hard;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dragonites.practice.hard.Candidate.Candidate;
import com.dragonites.practice.hard.Candidate.ElectionUtils;
import com.dragonites.practice.hard.Database.ElectionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragonites on 13/11/2016.
 */

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ListView listView = (ListView) view.findViewById(R.id.list_fragment);

        List<String> names = new ArrayList<>();
        List<String> parties = new ArrayList<>();

        ContentResolver resolver = getActivity().getContentResolver();

        String[] projection = new String[]{ElectionContract.Candidates.COL_NAME, ElectionContract.Candidates.COL_PARTY, ElectionContract.Candidates.COL_AGE, ElectionContract.Candidates.COL_VOTES};

        Cursor cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                null, null, null);

        if (cursor.getCount() == 0) {
            seedCandidateTable(); // Staat onderaan
            cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                    null, null, null);
        }

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String party = cursor.getString(1);

            names.add(name);
            parties.add(party);

            cursor.moveToNext();
        }

        MySimpleArrayAdapter customAdapter = new MySimpleArrayAdapter(getActivity(), names, parties);
        listView.setAdapter(customAdapter);

        return view;
    }

    private void seedCandidateTable() {
        List<Candidate> candidates = ElectionUtils.getCandidates(getActivity().getApplicationContext());
        ContentValues values = new ContentValues();
        ContentResolver resolver = getActivity().getContentResolver();

        for (Candidate c : candidates) {
            values.put(ElectionContract.Candidates.COL_NAME, c.getName());
            values.put(ElectionContract.Candidates.COL_PARTY, c.getParty());
            values.put(ElectionContract.Candidates.COL_AGE, c.getAge());
            values.put(ElectionContract.Candidates.COL_VOTES, c.getVotes());
            resolver.insert(ElectionContract.Candidates.CONTENT_URI, values);
        }

    }
}
