package com.openclassrooms.entrevoisins.ui.neighbour_list;

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
import com.openclassrooms.entrevoisins.ui.favorite_neighbour_list.FavNeighbourFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.List;
import java.util.Objects;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    public static RecyclerView mRecyclerView;

    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        return new NeighbourFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list_recyclerview, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));
        return view;
    }
    /**
     * Init the List of neighbours
     */
    private void initList() {
        List<Neighbour> mNeighbours = mApiService.getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, 0));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
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
        if (event.fragPosition == 0) {
            mApiService.deleteNeighbour(event.neighbour);
            if (mApiService.getFavoriteNeighbours().contains(event.neighbour))
                mApiService.deleteFavoriteNeighbour(event.neighbour);
        }
        initList();
    }
}

