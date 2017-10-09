package com.andnand.android.moivelist.data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.andnand.android.moivelist.Movie;

import java.util.UUID;

import static com.andnand.android.moivelist.data.MovieListContract.MovieListEntry;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieCursorWrapper extends CursorWrapper {

    public MovieCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Movie getMovie() {
        String uuidString = getString(getColumnIndex(MovieListEntry.UUID));
        String title = getString(getColumnIndex(MovieListEntry.TITLE));
        String poster = getString(getColumnIndex(MovieListEntry.POSTER));
        String year = getString(getColumnIndex(MovieListEntry.YEAR));
        String rated = getString(getColumnIndex(MovieListEntry.RATED));
        String imdb = getString(getColumnIndex(MovieListEntry.IMDBID));
        String langauge = getString(getColumnIndex(MovieListEntry.LANGUAGE));
        String ratings = getString(getColumnIndex(MovieListEntry.RATINGS));



        /*
        private String mRuntime;
        private String mGenre;
        private String mDirector;
        private String mWriter;
        private String mActors;
        private String mLanguage = "N/A";
        private String mRatings;
        private String mImdbId;
        private String mProduction;
        private String mWebsite;
        */


        Movie movie = new Movie(UUID.fromString(uuidString));
        movie.setTitle(title);
        movie.setPoster(poster);
        movie.setYear(year);
        movie.setRated(rated);
        movie.setImdbId(imdb);
        movie.setLanguage(langauge);
        movie.setRatings(ratings);

        return movie;

    }


}
