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


        Movie movie = new Movie(UUID.fromString(uuidString));
        movie.setTitle(title);

        return movie;

    }


}
