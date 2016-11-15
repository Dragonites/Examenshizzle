package com.dragonites.practice.hard.Database;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Dragonites on 15/11/2016.
 */

public class ElectionContract {
    public static final String DB_NAME = "election_db";
    public static final int DB_VER = 1;

    public static final class Candidates implements BaseColumns {
        public static final String TABLE_NAME = "candidates";
        public static final String COL_NAME = "name";
        public static final String COL_PARTY = "party";
        public static final String COL_AGE = "age";
        public static final String COL_VOTES = "votes";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONDIMENT).build();

        public static final String CONTENT_TYPE =
                "vnd.android.cursor.dir/" + CONTENT_URI + "/" + PATH_CONDIMENT;
        public static final String CONTENT_ITEM_TYPE =
                "vnd.android.cursor.item/" + CONTENT_URI + "/" + PATH_CONDIMENT;

        public static Uri buildCondimentUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final String CONTENT_AUTHORITY = "com.dragonites.practice.hard.candidatedatabase";

    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CONDIMENT = "condiment";
}
