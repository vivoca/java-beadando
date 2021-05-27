package hu.egyudv.beadando.ui.component.weather;

import hu.egyudv.beadando.model.WeatherData;

import javax.swing.*;
import java.awt.*;

public class SelectedWeatherPanel {
    private JPanel selectedWeatherPanel;
    private JLabel tempLabel;
    private JLabel feelsLikeLabel;
    private JLabel tempMinLabel;
    private JLabel tempMaxLabel;
    private JLabel pressureLabel;
    private JLabel humidityLabel;
    private JLabel windSpeedLabel;
    private JLabel windDegLabel;
    private JTextField tempTextField;
    private JTextField feelsLikeTextField;
    private JTextField tempMinTextField;
    private JTextField tempMaxTextField;
    private JTextField pressureTextField;
    private JTextField humidityTextField;
    private JTextField windSpeedTextField;
    private JTextField windDegTextField;

    private WeatherData selectedWeatherData;

    public SelectedWeatherPanel() {
        selectedWeatherPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(8, 2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        selectedWeatherPanel.setLayout(gridLayout);

        tempLabel = new JLabel("Temperature");
        selectedWeatherPanel.add(tempLabel);

        tempTextField = new JTextField();
        tempTextField.setEditable(false);
        selectedWeatherPanel.add(tempTextField);

        feelsLikeLabel = new JLabel("Feels Like");
        selectedWeatherPanel.add(feelsLikeLabel);

        feelsLikeTextField = new JTextField();
        feelsLikeTextField.setEditable(false);
        selectedWeatherPanel.add(feelsLikeTextField);

        tempMinLabel = new JLabel("Minimum Temp");
        selectedWeatherPanel.add(tempMinLabel);

        tempMinTextField = new JTextField();
        tempMinTextField.setEditable(false);
        selectedWeatherPanel.add(tempMinTextField);

        tempMaxLabel = new JLabel("Maximum Temp");
        selectedWeatherPanel.add(tempMaxLabel);

        tempMaxTextField = new JTextField();
        tempMaxTextField.setEditable(false);
        selectedWeatherPanel.add(tempMaxTextField);

        pressureLabel = new JLabel("Pressure");
        selectedWeatherPanel.add(pressureLabel);

        pressureTextField = new JTextField();
        pressureTextField.setEditable(false);
        selectedWeatherPanel.add(pressureTextField);

        humidityLabel = new JLabel("Humidity");
        selectedWeatherPanel.add(humidityLabel);

        humidityTextField = new JTextField();
        humidityTextField.setEditable(false);
        selectedWeatherPanel.add(humidityTextField);

        windSpeedLabel = new JLabel("Wind Speed");
        selectedWeatherPanel.add(windSpeedLabel);

        windSpeedTextField = new JTextField();
        windSpeedTextField.setEditable(false);
        selectedWeatherPanel.add(windSpeedTextField);

        windDegLabel = new JLabel("Wind Degree");
        selectedWeatherPanel.add(windDegLabel);

        windDegTextField = new JTextField();
        windDegTextField.setEditable(false);
        selectedWeatherPanel.add(windDegTextField);

    }

    public JPanel getSelectedWeatherPanel() {
        return selectedWeatherPanel;
    }

    public void handleSelectedWeatherChange(WeatherData weatherData) {
        if (weatherData == null) {
            selectedWeatherData = new WeatherData();
        } else {
            selectedWeatherData = weatherData;
        }
        tempTextField.setText(selectedWeatherData.getTemp() + " °C");
        feelsLikeTextField.setText(selectedWeatherData.getFeelsLike() + " °C");
        tempMinTextField.setText(selectedWeatherData.getTempMin() + " °C");
        tempMaxTextField.setText(selectedWeatherData.getTempMax() + " °C");
        pressureTextField.setText(selectedWeatherData.getPressure() + " kPa");
        humidityTextField.setText(selectedWeatherData.getHumidity() + "%");
        windSpeedTextField.setText(selectedWeatherData.getWindSpeed() + " m/s");
        windDegTextField.setText(selectedWeatherData.getWindDeg() + "°");

    }

}
