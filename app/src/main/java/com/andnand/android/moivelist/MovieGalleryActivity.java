package com.andnand.android.moivelist;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

public class MovieGalleryActivity extends SingleFragmentActivity implements MovieGalleryFragment.Callbacks {


    @Override
    protected Fragment createFragment() {
        return MovieGalleryFragment.newInstance();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = MovieActivity.newIntent(this, movie);
        startActivity(intent);
        /*
        Intent intent = MovieActivity.newIntent(this, movie.getUUID());
        startActivity(intent);
        */
    }
}
