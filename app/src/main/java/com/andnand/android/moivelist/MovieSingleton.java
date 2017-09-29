package com.andnand.android.moivelist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.andnand.android.moivelist.data.MovieCursorWrapper;
import com.andnand.android.moivelist.data.MovieDatabaseHelper;
import com.andnand.android.moivelist.data.MovieListContract;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.andnand.android.moivelist.data.MovieListContract.*;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieSingleton {

    private static MovieSingleton sMovieSingleton;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MovieSingleton get(Context context) {
        if (sMovieSingleton == null) {
            sMovieSingleton = new MovieSingleton(context);
        }
        return sMovieSingleton;
    }

    private MovieSingleton(Context context) {
        mContext = context;
        mDatabase = new MovieDatabaseHelper(mContext).getWritableDatabase();
    }

    public void addMovie(Movie movie) {
        ContentValues values = getContentValues(movie);
        mDatabase.insert(MovieListEntry.TABLE_NAME, null, values);
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();

        MovieCursorWrapper cursor = queryMovies(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                movies.add(cursor.getMovie());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return movies;
    }

    public Movie getMovie(UUID id) {

        MovieCursorWrapper cursor = queryMovies(
                MovieListEntry.UUID + " = ?",
                new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMovie();
        } finally {
            cursor.close();
        }
    }




    public void updateMovie(Movie movie) {
        String uuidString = movie.getUUID().toString();
        ContentValues contentValues = getContentValues(movie);

        mDatabase.update(MovieListEntry.TABLE_NAME, contentValues,
                MovieListEntry.UUID + " = ?", new String[] {uuidString});
    }


    private static ContentValues getContentValues(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieListEntry.UUID, movie.getUUID().toString());
        contentValues.put(MovieListEntry.TITLE, movie.getTitle());
        contentValues.put(MovieListEntry.YEAR, movie.getYear());
        contentValues.put(MovieListEntry.RATED, movie.getRated());
        contentValues.put(MovieListEntry.RUNTIME, movie.getRuntime());
        contentValues.put(MovieListEntry.GENRE, movie.getGenre());
        contentValues.put(MovieListEntry.DIRECTOR, movie.getDirector());
        contentValues.put(MovieListEntry.WRITER, movie.getWriter());
        contentValues.put(MovieListEntry.ACTORS, movie.getActors());
        contentValues.put(MovieListEntry.LANGUAGE, movie.getLanguage());
        contentValues.put(MovieListEntry.POSTER, movie.getPoster());
        contentValues.put(MovieListEntry.RATINGS, movie.getRatings());
        contentValues.put(MovieListEntry.IMDBID, movie.getImdbId());
        contentValues.put(MovieListEntry.PRODUCTION, movie.getProduction());
        contentValues.put(MovieListEntry.WEBSITE, movie.getWebsite());

        return contentValues;
    }

    private MovieCursorWrapper queryMovies(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                MovieListEntry.TABLE_NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MovieCursorWrapper(cursor);
    }

}
