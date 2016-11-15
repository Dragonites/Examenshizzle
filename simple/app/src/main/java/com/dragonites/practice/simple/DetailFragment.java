package com.dragonites.practice.simple;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dragonites on 14/11/2016.
 */

public class DetailFragment extends Fragment{
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
        String[] parties = res.getStringArray(R.array.parties);
        String[] names = res.getStringArray(R.array.names);
        String[] ages = res.getStringArray(R.array.ages);
        String[] votes = res.getStringArray(R.array.votes);

        String party = parties[position];
        String name = names[position];
        String age = ages[position];
        String vote = votes[position];

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
    }
}
