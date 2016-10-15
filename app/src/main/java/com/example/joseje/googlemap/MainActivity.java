package com.example.joseje.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.joseje.googlemap.R.id.place_title;

public class MainActivity extends AppCompatActivity implements ListClickElement{

    RecyclerView mPlaceListRecycleView;
    private LinearLayoutManager mLayoutManager;
    private List<Place> mPlaceList;
    private PlaceListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlaceListRecycleView = (RecyclerView) findViewById(R.id.recycle_view);

        mLayoutManager = new LinearLayoutManager(this);
        mPlaceListRecycleView.setLayoutManager(mLayoutManager);
        generatePlaceList();

        mAdapter = new PlaceListAdapter(mPlaceList, this);
        mPlaceListRecycleView.setAdapter(mAdapter);
    }

    private void generatePlaceList() {
        mPlaceList = new ArrayList<>();
        mPlaceList.add(new Place("Casa" , 29.058503, -110.965025));
        mPlaceList.add(new Place("Nearsoft", 29.097437, -111.022033));

    }


    @Override
    public void onItemClick(Place place) {
        MapsActivity.start(this, place);
    }
}
