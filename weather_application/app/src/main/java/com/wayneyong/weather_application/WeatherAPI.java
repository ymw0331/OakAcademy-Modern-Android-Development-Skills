package com.wayneyong.weather_application;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPI {

    //http://api.openweathermap.org/data/2.5/weather?lat=51.5287352&lon=-0.3817841&appid=c05013f984d1c00a89b1ee7fa7bdb99c&units=metric#
    //get data by location and city name
    @GET("weather?appid=c05013f984d1c00a89b1ee7fa7bdb99c&units=metric")
    Call<OpenWeatherMap> getWeatherWithLocation(@Query("lat") double lat, @Query("lon") double lon);

    //http://api.openweathermap.org/data/2.5/weather?q=Kuala%20Lumpur&appid=c05013f984d1c00a89b1ee7fa7bdb99c
    @GET("weather?appid=c05013f984d1c00a89b1ee7fa7bdb99c&units=metric")
    Call<OpenWeatherMap> getWeatherWithCityName(@Query("q") String name);

}
