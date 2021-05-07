package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.repository.HikingRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.HikingRepository;

import java.util.List;

public class HikingServiceImpl implements HikingService {

    private final HikingRepository hikingRepository = new HikingRepositoryFile();

    @Override
    public List<Hiking> all() {
        return hikingRepository.all();
    }

    @Override
    public Hiking save(Hiking object) {
        return hikingRepository.save(object);
    }

    @Override
    public void delete(String id) {
        hikingRepository.delete(id);
    }

    @Override
    public Hiking get(String id) {
        return hikingRepository.get(id);
    }

    @Override
    public WeatherData getRelatedWeatherData(String location) {
        WeatherService weatherService = new WeatherServiceImpl();
        try {
            return weatherService.getDataByCity(location);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
