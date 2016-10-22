package com.example.joseje.googlemap;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailPlaceFragment extends Fragment {

    private  Place mPlace;

    public DetailPlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mPlace = getActivity().getIntent().getParcelableExtra(MapsActivity.PLACE_EXTRA_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_detail_place, container, false);

        ImageView placeImageView = (ImageView) v.findViewById(R.id.place_image_view);
        TextView PlaceNameTextView = (TextView) v.findViewById(R.id.place_name_attribute);
        TextView PlaceRankingTextView = (TextView) v.findViewById(R.id.place_ranking_attribute);
        Button mapButton = (Button) v.findViewById(R.id.map_button);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsActivity.start(getActivity(), mPlace);
            }
        });

        Picasso.with(getActivity()).load(mPlace.getImageUrl()).into(placeImageView);
        PlaceNameTextView.setText(mPlace.getName());
        PlaceRankingTextView.setText(String.valueOf(mPlace.getRanking()));

        return v;
    }

}
