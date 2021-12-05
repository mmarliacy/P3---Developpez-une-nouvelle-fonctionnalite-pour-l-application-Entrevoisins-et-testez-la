package com.openclassrooms.entrevoisins.ui.favorite_neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavNeighbourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavNeighbourFragment extends Fragment {


    public static RecyclerView favRecyclerview;
    private NeighbourApiService mApiService;

    public static FavNeighbourFragment newInstance() {
        return new FavNeighbourFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_recyclerview, container, false);
        Context context = view.getContext();
        favRecyclerview = (RecyclerView) view;
        favRecyclerview.setLayoutManager(new LinearLayoutManager(context));
        favRecyclerview.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initFavoriteList() {
        List<Neighbour> mFavNeighbours = mApiService.getFavoriteNeighbours();
        favRecyclerview.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavNeighbours, 1));
    }

    @Override
    public void onResume() {
        super.onResume();
        initFavoriteList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if (event.fragPosition == 1) {
            mApiService.deleteFavoriteNeighbour(event.neighbour);
            if (mApiService.getNeighbours().contains(event.neighbour))
                mApiService.deleteNeighbour(event.neighbour);
        }
        initFavoriteList();
    }
}


