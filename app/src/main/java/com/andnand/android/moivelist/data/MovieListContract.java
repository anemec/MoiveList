package com.andnand.android.moivelist.data;

import android.provider.BaseColumns;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieListContract {

    private MovieListContract() {
    }

    public static final class MovieListEntry implements BaseColumns {
        public static final String TABLE_NAME = "movieList";
        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String YEAR = "year";
        public static final String RATED = "rated";
        public static final String RUNTIME = "runtime";
        public static final String GENRE = "genre";
        public static final String DIRECTOR = "director";
        public static final String WRITER = "writer";
        public static final String ACTORS = "actors";
        public static final String LANGUAGE = "language";
        public static final String POSTER = "poster";
        public static final String RATINGS = "ratings";
        public static final String IMDBID = "imdbid";
        public static final String PRODUCTION = "production";
        public static final String WEBSITE = "website";

    }



}
