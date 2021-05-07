package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.WeatherData;

import java.io.IOException;

public interface WeatherService {

    WeatherData getDataByCity(String city) throws IOException;

}
