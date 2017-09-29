package com.andnand.android.moivelist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.andnand.android.moivelist.data.MovieListContract.MovieListEntry;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movieDatabase.db";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_LIST_TABLE = "CREATE TABLE " +
                MovieListEntry.TABLE_NAME + " (" +
                MovieListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                MovieListEntry.UUID + ", " +
                MovieListEntry.TITLE + ", " +
                MovieListEntry.YEAR + ", " +
                MovieListEntry.RATED + ", " +
                MovieListEntry.RUNTIME + ", " +
                MovieListEntry.GENRE + ", " +
                MovieListEntry.DIRECTOR + ", " +
                MovieListEntry.WRITER + ", " +
                MovieListEntry.ACTORS + ", " +
                MovieListEntry.LANGUAGE + ", " +
                MovieListEntry.POSTER + ", " +
                MovieListEntry.RATINGS + ", " +
                MovieListEntry.IMDBID + ", " +
                MovieListEntry.PRODUCTION + ", " +
                MovieListEntry.WEBSITE +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieListEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
