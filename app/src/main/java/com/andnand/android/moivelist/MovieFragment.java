package com.andnand.android.moivelist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieFragment extends Fragment {

    private static final String LOG_TAG = "MovieFragment";

    private static final String ARG_MOVIE_ID = "movie_id";

    private Movie mMovie;
    private Movie mFullMovie;
    private TextView mTitleTextView;
    private TextView mLanguageTextView;
    private TextView mRatingsTextView;
    private ImageView mPosterImageView;

    public static MovieFragment newInstance(Movie movie) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE_ID, (Serializable) movie);

        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = (Movie) getArguments().getSerializable(ARG_MOVIE_ID);

        //TODO change so no api call when loading from database

        new FetchMovieItemTask(mMovie.getImdbId()).execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie , container, false);
        setHasOptionsMenu(true);

        mTitleTextView = view.findViewById(R.id.movie_title_text_view);
        mTitleTextView.setText(mMovie.getTitle());

        mLanguageTextView = view.findViewById(R.id.movie_language_text_view);
        mLanguageTextView.setText(mMovie.getLanguage());
        mRatingsTextView = view.findViewById(R.id.movie_ratings_text_view);
        //String s = imdbRating(mMovie.getRatings());
        //mRatingsTextView.setText(s);
        mRatingsTextView.setText(mMovie.getRatings());


        mPosterImageView = view.findViewById(R.id.movie_poster_image_view);
        Picasso.with(getActivity())
                .load(mMovie.getPoster())
                .into(mPosterImageView);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_movie_menu, menu);
        Log.i(LOG_TAG, "onCreateOptionsMenu");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fragment_movie_add:
                Log.i(LOG_TAG, "Adding movie with poster of " + mFullMovie.getPoster());
                MovieSingleton.get(getActivity()).addMovie(mFullMovie);
                Log.i(LOG_TAG, "Adding to database");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class FetchMovieItemTask extends AsyncTask<Void, Void, Movie> {
        private String mImdbID;

        public FetchMovieItemTask(String imdbID) {
            mImdbID = imdbID;
        }


        @Override
        protected Movie doInBackground(Void... params) {
            return new MovieFetcher().fetchMovie(mImdbID);
        }

        @Override
        protected void onPostExecute(Movie movie) {
            Log.i(LOG_TAG, "Movie title onPost " + movie.getTitle());
            mFullMovie = movie;
            updateView();
        }
    }

    private void updateView() {
        mLanguageTextView.setText(mFullMovie.getLanguage());
        mRatingsTextView.setText(mFullMovie.getRatings());
    }

    private String imdbRating(String ratings) {
        String retVal;
        JsonElement jsonElement = new JsonParser().parse(ratings);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        JsonElement jsonElement2 = jsonArray.get(0);
        JsonObject jsonObject = jsonElement2.getAsJsonObject();
        Log.i(LOG_TAG, "Ratings text " + jsonArray.get(0).toString());
        Log.i(LOG_TAG, "1st " + jsonElement2.toString());
        retVal = jsonObject.get("Value").toString();
        return retVal;
    }


}
