package com.example.joseje.googlemap.retrofit;

import com.example.joseje.googlemap.Places;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Erick on 22/10/2016.
 */

public interface PlacesService {

    @GET("places")
    Call<Places> getPlaces();
}
