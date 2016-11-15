package com.dragonites.practice.hard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Dragonites on 14/11/2016.
 */

public class DetailActivity extends Activity implements DetailListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();

        int position = intent.getIntExtra("position", -1);

        if (position != -1) {
            setFieldsAndPicture(position);
        }
    }
    @Override
    public void setFieldsAndPicture(int position) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        detailFragment.setAllFieldsAndImageFromPosition(position);
    }

    public void voteButtonClicked(View view) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        detailFragment.upVote();
    }
}
