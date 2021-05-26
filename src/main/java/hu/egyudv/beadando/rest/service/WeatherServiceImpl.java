package hu.egyudv.beadando.rest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.rest.model.WeatherItem;
import hu.egyudv.beadando.rest.model.WeatherListResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WeatherServiceImpl implements WeatherService {


    @Override
    public WeatherData getDataByCity(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/find?q=" + city + "&units=metric")
                .get()
                .addHeader("x-rapidapi-key", "6bc98090b0msha8afca49eb877fcp13cceajsnafbfc9cd9af7")
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherListResponse entity = objectMapper.readValue(response.body().string(), WeatherListResponse.class);

        WeatherItem weatherItem = entity.getList().get(0);

        WeatherData weatherData = new WeatherData();
        weatherData.setLocation(weatherItem.getName());
        weatherData.setTemp(weatherItem.getMain().getTemp());
        weatherData.setFeelsLike(weatherItem.getMain().getFeels_like());
        weatherData.setTempMin(weatherItem.getMain().getTemp_min());
        weatherData.setTempMax(weatherItem.getMain().getTemp_max());
        weatherData.setPressure(weatherItem.getMain().getPressure());
        weatherData.setHumidity(weatherItem.getMain().getHumidity());
        weatherData.setWindSpeed(weatherItem.getWind().getSpeed());
        weatherData.setWindDeg(weatherItem.getWind().getDeg());

        return weatherData;
    }
}
