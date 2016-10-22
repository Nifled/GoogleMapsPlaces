package com.example.joseje.googlemap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.joseje.googlemap.retrofit.PlacesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.joseje.googlemap.R.id.place_title;

public class MainActivity extends AppCompatActivity implements ListClickElement, Callback<Places> {

    RecyclerView mPlaceListRecycleView;
    private LinearLayoutManager mLayoutManager;
    private List<Place> mPlaceList;
    private PlaceListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generatePlaceList();
    }

    private void setPlacesList() {
        mPlaceListRecycleView = (RecyclerView) findViewById(R.id.recycle_view);

        mLayoutManager = new LinearLayoutManager(this);
        mPlaceListRecycleView.setLayoutManager(mLayoutManager);

        mAdapter = new PlaceListAdapter(mPlaceList, this);
        mPlaceListRecycleView.setAdapter(mAdapter);
    }

    private void generatePlaceList() {
//        mPlaceList = new ArrayList<>();
//        mPlaceList.add(new Place("Casa" , 29.058503, -110.965025));
//        mPlaceList.add(new Place("Nearsoft", 29.097437, -111.022033));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://demo2355296.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        PlacesService service = retrofit.create(PlacesService.class);

        Call<Places> call = service.getPlaces();
        call.enqueue(this);

    }


    @Override
    public void onItemClick(Place place) {
        DetailPlaceActivity.start(this, place);
    }

    @Override
    public void onResponse(Call<Places> call, Response<Places> response) {
        mPlaceList = response.body().places;

        setPlacesList();
    }

    @Override
    public void onFailure(Call<Places> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }
}
