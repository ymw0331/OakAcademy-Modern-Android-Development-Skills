package com.wayneyong.weather_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    String API_Key = BuildConfig.OW_ApiKey;

    @GET("weather?appid=" + API_Key + "&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat") double lat, @Query("lon") double lon);

    @GET("weather?appid=" + API_Key + "&units=metric")
    Call<OpenWeatherMap> getWeatherWithCityName(@Query("q") String name);

}
