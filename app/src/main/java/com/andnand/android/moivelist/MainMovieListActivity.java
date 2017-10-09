package com.andnand.android.moivelist;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by andrew on 9/27/17.
 */

public class MainMovieListActivity extends SingleFragmentActivity implements MainMovieListFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return MainMovieListFragment.newInstance();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = MovieActivity.newIntent(this, movie);
        startActivity(intent);
    }
}
