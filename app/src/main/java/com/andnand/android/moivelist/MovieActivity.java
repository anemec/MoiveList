package com.andnand.android.moivelist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by andrew on 9/28/17.
 */

public class MovieActivity extends SingleFragmentActivity {

    private static final String EXTRA_MOVIE_ID = "com.andnand.android.movielist.movie_id";

    public static Intent newIntent(Context packageContext, Movie movie) {
        Intent intent = new Intent(packageContext, MovieActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, (Serializable) movie);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return MovieFragment.newInstance((Movie) getIntent().getSerializableExtra(EXTRA_MOVIE_ID));
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        UUID movieId = (UUID) getIntent().getSerializableExtra(EXTRA_MOVIE_ID);
    }
    */
}
