package com.dragonites.practice.hard.Database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dragonites.practice.hard.Candidate.Candidate;
import com.dragonites.practice.hard.Candidate.ElectionUtils;

import java.util.List;

/**
 * Created by Dragonites on 15/11/2016.
 */

public class CandidateDBHelper extends SQLiteOpenHelper {
    private Context context;

    private static final int DATABASE_VERSION = ElectionContract.DB_VER;
    private static final String DATABASE_NAME = ElectionContract.DB_NAME;

    public CandidateDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Maak database
        addCandidateTable(db);
        // Voorzie data in database
//        seedCandidateTable();
    }

    private void addCandidateTable(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ElectionContract.Candidates.TABLE_NAME + " (" +
                        ElectionContract.Candidates._ID + " INTEGER PRIMARY KEY, " +
                        ElectionContract.Candidates.COL_NAME + " TEXT NOT NULL, " +
                        ElectionContract.Candidates.COL_PARTY + " TEXT NOT NULL, " +
                        ElectionContract.Candidates.COL_AGE + " INTEGER NOT NULL, " +
                        ElectionContract.Candidates.COL_VOTES + " INTEGER NOT NULL);"
        );
    }

    private void seedCandidateTable() {

        List<Candidate> candidates = ElectionUtils.getCandidates(context);
        ContentValues values = new ContentValues();
        ContentResolver resolver = context.getContentResolver();

        for (Candidate c : candidates) {
            values.put(ElectionContract.Candidates.COL_NAME, c.getName());
            values.put(ElectionContract.Candidates.COL_PARTY, c.getParty());
            values.put(ElectionContract.Candidates.COL_AGE, c.getAge());
            values.put(ElectionContract.Candidates.COL_VOTES, c.getVotes());
            resolver.insert(ElectionContract.Candidates.CONTENT_URI, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        upgradeCandidateTable(db);
    }

    private void upgradeCandidateTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + ElectionContract.Candidates.TABLE_NAME);
        onCreate(db);
    }

}
