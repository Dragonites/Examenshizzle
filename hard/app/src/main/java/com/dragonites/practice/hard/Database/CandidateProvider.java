package com.dragonites.practice.hard.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by Dragonites on 15/11/2016.
 */

public class CandidateProvider extends ContentProvider{

    private static final int CONDIMENT = 100;
    private static final int CONDIMENT_ID = 101;

    private CandidateDBHelper mOpenHelper;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private static UriMatcher buildUriMatcher() {
        String content = ElectionContract.CONTENT_AUTHORITY;

        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(content, ElectionContract.PATH_CONDIMENT, CONDIMENT);
        matcher.addURI(content, ElectionContract.PATH_CONDIMENT + "/#", CONDIMENT_ID);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new CandidateDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case CONDIMENT:
                return ElectionContract.Candidates.CONTENT_TYPE;
            case CONDIMENT_ID:
                return ElectionContract.Candidates.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case CONDIMENT:
                retCursor = db.query(
                        ElectionContract.Candidates.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CONDIMENT_ID:
                _id = ContentUris.parseId(uri);
                retCursor = db.query(
                        ElectionContract.Candidates.TABLE_NAME,
                        projection,
                        ElectionContract.Candidates._ID + " = ?",
                        new String[]{String.valueOf(_id)},
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unkown URI: " + uri);
        }

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        long _id;
        Uri returnUri;

        switch(sUriMatcher.match(uri)){
            case CONDIMENT:
                _id = db.insert(ElectionContract.Candidates.TABLE_NAME, null, values);
                if(_id > 0){
                    returnUri =  ElectionContract.Candidates.buildCondimentUri(_id);
                } else{
                    throw new UnsupportedOperationException("Unable to insert rows into: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch(sUriMatcher.match(uri)){
            case CONDIMENT:
                rows = db.delete(ElectionContract.Candidates.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(selection == null || rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int rows;

        switch(sUriMatcher.match(uri)){
            case CONDIMENT:
                rows = db.update(ElectionContract.Candidates.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if(rows != 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rows;
    }
}
