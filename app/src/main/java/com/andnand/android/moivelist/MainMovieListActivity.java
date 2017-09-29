package com.andnand.android.moivelist;

import android.support.v4.app.Fragment;

/**
 * Created by andrew on 9/27/17.
 */

public class MainMovieListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainMovieListFragment.newInstance();
    }
}
