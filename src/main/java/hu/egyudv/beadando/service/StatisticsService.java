package hu.egyudv.beadando.service;

import hu.egyudv.beadando.model.UserData;

import java.util.List;

public interface StatisticsService {

    List<UserData> statMoreThan5Hiking();
    List<UserData> statAgeBetween15And20();
    List<UserData> statBornInJuly();
    List<UserData> statCompletedMediumHike();
}
