package com.andnand.android.moivelist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by andrew on 9/7/17.
 */

public class MovieGalleryFragment extends Fragment {

    private static final String LOG_TAG = "MovieGalleryFragment";

    private RecyclerView mMovieRecyclerView;
    private List<MoveItem> mItems = new ArrayList<>();
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onMovieSelected(Movie movie);
    }

    public static MovieGalleryFragment newInstance() {
        return new MovieGalleryFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        updateItems("");

        Log.i(LOG_TAG, "Background thread started");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_gallery, container, false);

        mMovieRecyclerView = view.findViewById(R.id.fragment_movie_gallery_recycler_view);
        mMovieRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        setupAdapter();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_movie_gallery, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(LOG_TAG, "QueryTextSubmit: " + query);
                updateItems(query);
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void updateItems(String query) {

        new FetchMovieItemsTask(query).execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "Background thread destroyed");
    }

    /* Recyclerview adapter */

    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mItemImageView;
        private MoveItem mMovie;

        public PhotoHolder(View itemView) {
            super(itemView);

            mItemImageView = itemView.findViewById(R.id.fragment_moive_item_image_view);
            itemView.setOnClickListener(this);
        }

        public void bindMovieItem(MoveItem movieItem) {
            mMovie = movieItem;
            Picasso.with(getActivity())
                    .load(movieItem.getImage())
                    .placeholder(R.drawable.github)
                    .into(mItemImageView);
        }

        @Override
        public void onClick(View view) {
            mCallbacks.onMovieSelected(mMovie);
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<MoveItem> mMoveItems;

        public PhotoAdapter(List<MoveItem> moveItems) {
            mMoveItems = moveItems;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position) {
            MoveItem moveItem = mMoveItems.get(position);
            photoHolder.bindMovieItem(moveItem);
        }

        @Override
        public int getItemCount() {
            return mMoveItems.size();
        }
    }

    /* End adapter and holder */

    private void setupAdapter() {
        if (isAdded()) {
            mMovieRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }


    private class FetchMovieItemsTask extends AsyncTask<Void, Void, List<MoveItem>> {
        private String mQuery;

        public FetchMovieItemsTask(String query) {
            mQuery = query;
        }


        @Override
        protected List<MoveItem> doInBackground(Void... params) {
            return new MovieFetcher().fetchItems(mQuery);
        }

        @Override
        protected void onPostExecute(List<MoveItem> moveItems) {
            mItems = moveItems;
            setupAdapter();
        }
    }
}
