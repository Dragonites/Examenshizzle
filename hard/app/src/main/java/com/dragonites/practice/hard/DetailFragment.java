package com.dragonites.practice.hard;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dragonites.practice.hard.Database.ElectionContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragonites on 14/11/2016.
 */

public class DetailFragment extends Fragment {
    private TextView partyField, nameField, ageField, votesField;
    private ImageView candidatePictureField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.detail_fragment, container, false);

        partyField = (TextView) fragmentView.findViewById(R.id.candidate_party);
        nameField = (TextView) fragmentView.findViewById(R.id.candidate_name);
        ageField = (TextView) fragmentView.findViewById(R.id.candidate_age);
        votesField = (TextView) fragmentView.findViewById(R.id.votes);
        candidatePictureField = (ImageView) fragmentView.findViewById(R.id.candidate_picture);

        return fragmentView;
    }

    public void setAllFieldsAndImageFromPosition(int position) {
        Resources res = getResources();
        List<String> names = new ArrayList<>();
        List<String> parties = new ArrayList<>();
        List<Integer> ages = new ArrayList<>();
        List<Integer> votes = new ArrayList<>();

        ContentResolver resolver = getActivity().getContentResolver();

        String[] projection = new String[]{ElectionContract.Candidates.COL_NAME, ElectionContract.Candidates.COL_PARTY, ElectionContract.Candidates.COL_AGE, ElectionContract.Candidates.COL_VOTES};

        Cursor cursor = resolver.query(ElectionContract.Candidates.CONTENT_URI, projection,
                null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(0);
            String party = cursor.getString(1);
            int age = cursor.getInt(2);
            int vote = cursor.getInt(3);

            names.add(name);
            parties.add(party);
            ages.add(age);
            votes.add(vote);

            cursor.moveToNext();
        }

        String party = parties.get(position);
        String name = names.get(position);
        String age = ages.get(position).toString();
        String vote = votes.get(position).toString();

        String[] splittedName = name.split(" ");
        String imageName = splittedName[0].toLowerCase() + "_" + splittedName[1].toLowerCase();

        partyField.setText(party);
        nameField.setText(name);
        ageField.setText(age);
        votesField.setText(vote);

        int checkIfImageExcist = res.getIdentifier(imageName, "drawable", getActivity().getPackageName());
        if (checkIfImageExcist != 0) {
            candidatePictureField.setImageResource(checkIfImageExcist);
        } else {
            candidatePictureField.setImageResource(R.drawable.usa_disc);
        }

        switch (party) {
            case "Republican Party":
                candidatePictureField.setBackgroundColor(Color.RED);
                break;
            case "Democratic Party":
                candidatePictureField.setBackgroundColor(Color.BLUE);
                break;
            default:
                candidatePictureField.setBackgroundColor(Color.GREEN);
                break;
        }

    }

    public void upVote() {
        String currentVote = votesField.getText().toString();
        int currentVoteValue = Integer.parseInt(currentVote);
        currentVoteValue++;
        votesField.setText(currentVoteValue + "");

        ContentValues contentValues = new ContentValues();
        ContentResolver contentResolver = getActivity().getContentResolver();

        contentValues.put(ElectionContract.Candidates.COL_NAME, nameField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_PARTY, partyField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_AGE, ageField.getText().toString());
        contentValues.put(ElectionContract.Candidates.COL_VOTES, votesField.getText().toString());

        contentResolver.update(ElectionContract.Candidates.CONTENT_URI, contentValues, "name='" + nameField.getText().toString() + "'", null);

        Toast.makeText(this.getActivity(), "saved", Toast.LENGTH_SHORT).show();
    }
}
