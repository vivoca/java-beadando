package hu.egyudv.beadando.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherItem {

    private Long id;
    private String name;
    private WeatherMain main;
    private WeatherWindData wind;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherMain getMain() {
        return main;
    }

    public void setMain(WeatherMain main) {
        this.main = main;
    }

    public WeatherWindData getWind() {
        return wind;
    }

    public void setWind(WeatherWindData wind) {
        this.wind = wind;
    }
}
