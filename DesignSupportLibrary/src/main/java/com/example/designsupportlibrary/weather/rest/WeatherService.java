package com.example.designsupportlibrary.weather.rest;

import com.example.designsupportlibrary.weather.model.WeatherData;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherService {

    @GET("/data/2.5/weather")
    public Call<WeatherData> getWeather(@Query("q") String q, @Query("appid") String appid);
}
