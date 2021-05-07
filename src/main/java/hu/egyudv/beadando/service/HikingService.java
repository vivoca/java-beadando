package hu.egyudv.beadando.service;


import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;

import java.util.List;

public interface HikingService extends GeneralService<Hiking> {

    WeatherData getRelatedWeatherData(String location);

}
