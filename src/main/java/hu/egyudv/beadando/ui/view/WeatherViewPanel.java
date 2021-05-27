package hu.egyudv.beadando.ui.view;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.WeatherData;
import hu.egyudv.beadando.service.HikingService;
import hu.egyudv.beadando.service.HikingServiceImpl;
import hu.egyudv.beadando.ui.component.weather.SelectedWeatherPanel;
import hu.egyudv.beadando.ui.component.SignaturePanel;
import hu.egyudv.beadando.ui.component.hiking.HikingTableModel;
import hu.egyudv.beadando.ui.component.hiking.SelectedHikingPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeatherViewPanel {
    private JPanel weatherPanel;
    private JTable hikingTable;
    private JButton refreshBtn;

    private HikingData selectedHiking;
    private SelectedHikingPanel selectedHikingPanel;
    private List<HikingData> hikingList = new ArrayList<>();
    private SelectedWeatherPanel selectedWeatherPanel;

    public WeatherViewPanel() {
        Insets defaultInsets = new Insets(5,25,5,25);

        weatherPanel = new JPanel();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        weatherPanel.setLayout(new GridBagLayout());

        refreshBtn = createRefreshBtn();
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = defaultInsets;
        layoutConstraints.anchor = GridBagConstraints.LINE_START;
        weatherPanel.add(refreshBtn, layoutConstraints);

        layoutConstraints.anchor = GridBagConstraints.CENTER;

        JScrollPane hikingTablePanel = createHikingTable();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = defaultInsets;
        weatherPanel.add(hikingTablePanel, layoutConstraints);

        JPanel hikingPanel = createSelectedHikingPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        weatherPanel.add(hikingPanel, layoutConstraints);

        JPanel currentWeatherPanel = createSelectedWeatherPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        weatherPanel.add(currentWeatherPanel, layoutConstraints);

        JPanel signPanel = createSignaturePanel();
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        weatherPanel.add(signPanel, layoutConstraints);

        refreshHikingList();

    }

    public JPanel getWeatherPanel() {
        return weatherPanel;
    }

    public void refreshHikingList() {
        HikingService hikingService = new HikingServiceImpl();
        hikingList = hikingService.all();
        hikingTable.setModel(new HikingTableModel(hikingList));
    }

    private JScrollPane createHikingTable() {
        hikingTable = new JTable();
        hikingTable.setModel(new HikingTableModel(hikingList));
        hikingTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
        hikingTable.setFillsViewportHeight(true);
        hikingTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                int rowNum = hikingTable.getSelectedRow();
                if (rowNum >= 0) {
                    selectedHiking = hikingList.get(hikingTable.getSelectedRow());
                }
                selectedHikingPanel.handleSelectedHikingChange(selectedHiking);

                HikingService hikingService = new HikingServiceImpl();
                WeatherData weatherData = hikingService.getRelatedWeatherData(selectedHiking.getLocation());
                selectedWeatherPanel.handleSelectedWeatherChange(weatherData);
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane hikingScrollPane = new JScrollPane(hikingTable);
        hikingScrollPane.setViewportView(hikingTable);
        return hikingScrollPane;
    }

    private JPanel createSelectedHikingPanel() {
        selectedHikingPanel = new SelectedHikingPanel(false);
        return selectedHikingPanel.getSelectedHikingPanel();
    }

    private JPanel createSelectedWeatherPanel() {
        selectedWeatherPanel = new SelectedWeatherPanel();
        return selectedWeatherPanel.getSelectedWeatherPanel();
    }

    private JButton createRefreshBtn() {
        refreshBtn = new JButton("Refresh Table Data");
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });
        return refreshBtn;
    }

    public void refreshTableData() {
        HikingService hikingService = new HikingServiceImpl();
        hikingList = hikingService.all();
        hikingTable.setModel(new HikingTableModel(hikingList));
    }

    private JPanel createSignaturePanel() {
        return new SignaturePanel().getSignaturePanel();
    }

}
