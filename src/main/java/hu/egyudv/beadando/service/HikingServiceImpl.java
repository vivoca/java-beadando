package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.repository.db.HikingRepositoryDb;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.db.entity.Hiking;
import hu.egyudv.beadando.rest.service.WeatherService;
import hu.egyudv.beadando.rest.service.WeatherServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class HikingServiceImpl implements HikingService {

    private final HikingRepository hikingRepository = new HikingRepositoryDb();

    @Override
    public List<HikingData> all() {
        List<Hiking> hikingList = hikingRepository.all();
        List<HikingData> resultList = new ArrayList<>();
        for (Hiking object : hikingList) {
            resultList.add(convertToHikingData(object));
        }
        return resultList;
    }

    @Override
    public HikingData save(HikingData object) {
        Hiking hiking = convertToHiking(object);
        hiking = hikingRepository.save(hiking);
        return convertToHikingData(hiking);
    }

    @Override
    public void delete(long id) {
        hikingRepository.delete(id);
    }

    @Override
    public HikingData get(long id) {
        return convertToHikingData(hikingRepository.get(id));
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

    @Override
    public HikingData convertToHikingData(Hiking hiking) {
        HikingData hikingData = new HikingData();
        hikingData.setId(hiking.getId());
        hikingData.setName(hiking.getName());
        hikingData.setDescription(hiking.getDescription());
        hikingData.setLength(hiking.getLength());
        hikingData.setDifficulty(hiking.getDifficulty());
        hikingData.setLocation(hiking.getLocation());
        return hikingData;
    }

    private Hiking convertToHiking(HikingData hikingData) {
        Hiking hiking = new Hiking();
        hiking.setId(hikingData.getId());
        hiking.setName(hikingData.getName());
        hiking.setDescription(hikingData.getDescription());
        hiking.setLength(hikingData.getLength());
        hiking.setDifficulty(hikingData.getDifficulty());
        hiking.setLocation(hikingData.getLocation());
        return hiking;
    }
}
