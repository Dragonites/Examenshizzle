package com.dragonites.practice.simple;

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
        // Roep de functie in DetailFragment aan
        detailFragment.setAllFieldsAndImageFromPosition(position);
    }

    public void voteButtonClicked(View view) {
        DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.fragment_container2);
        // Roep de functie op om vote te verhogen, deze wordt in de 'easy'-versie niet opgeslagen
        detailFragment.upVote();
    }
}
