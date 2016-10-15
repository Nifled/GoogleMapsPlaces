package com.example.joseje.googlemap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by joseje on 10/15/16.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {

    private ListClickElement mListClickElement;
    private List<Place> mPlaceList;

    PlaceListAdapter(List<Place> placeList, ListClickElement listClickElement){
        mListClickElement = listClickElement;
        mPlaceList = placeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_info_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Place place = mPlaceList.get(position);
        String name = place.getName();
        Double latitud = place.getLatitude();
        Double longitud = place.getLongitude();
        holder.titleTextView.setText(name);
        holder.latlong.setText("Latitud: " +  latitud + ", \nLongitud: " + longitud);

        holder.titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListClickElement.onItemClick(place);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPlaceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView latlong;
        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView)itemView.findViewById(R.id.place_title);
            latlong = (TextView)itemView.findViewById(R.id.latlong);
        }
    }
}
