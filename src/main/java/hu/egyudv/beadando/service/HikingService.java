package hu.egyudv.beadando.service;


import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.repository.db.entity.Hiking;

public interface HikingService extends GeneralService<HikingData> {

    WeatherData getRelatedWeatherData(String location);
    HikingData convertToHikingData(Hiking hiking);

}
