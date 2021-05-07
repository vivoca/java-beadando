package hu.egyudv.beadando.ui.view;

import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.HikingRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.service.HikingService;
import hu.egyudv.beadando.service.HikingServiceImpl;
import hu.egyudv.beadando.service.UserHikingService;
import hu.egyudv.beadando.service.UserHikingServiceImpl;
import hu.egyudv.beadando.ui.component.hiking.HikingActionPanel;
import hu.egyudv.beadando.ui.component.hiking.HikingTableModel;
import hu.egyudv.beadando.ui.component.hiking.SelectedHikingPanel;
import hu.egyudv.beadando.ui.component.user.UserTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HikingViewPanel {
    private JPanel hikingPanel;
    private JTable hikingTable;
    private JTable userTable;

    private Hiking selectedHiking;
    private List<Hiking> hikingList = new ArrayList<>();
    private SelectedHikingPanel selectedHikingPanel;


    public HikingViewPanel() {
        Insets defaultInsets = new Insets(5, 25, 5, 25);

        hikingPanel = new JPanel();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        hikingPanel.setLayout(new GridBagLayout());

        JButton refreshBtn = new JButton("Refresh Hiking Table");
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = defaultInsets;
        layoutConstraints.anchor = GridBagConstraints.LINE_START;
        hikingPanel.add(refreshBtn, layoutConstraints);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshHikingList();
            }
        });
        //default
        layoutConstraints.anchor = GridBagConstraints.CENTER;

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
                userTable.setModel(new UserTableModel(new ArrayList<>()));
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(hikingTable);
        scrollPane.setViewportView(hikingTable);


        //Add the scroll pane to this panel.
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = defaultInsets;
        hikingPanel.add(scrollPane, layoutConstraints);

        JPanel actionPanel = new JPanel();
        GridLayout actionGridLayout = new GridLayout(0, 2);
        actionGridLayout.setHgap(5);
        actionGridLayout.setVgap(5);
        actionPanel.setLayout(actionGridLayout);

        selectedHikingPanel = new SelectedHikingPanel(true);
        actionPanel.add(selectedHikingPanel.getSelectedHikingPanel());
        actionPanel.add(new HikingActionPanel(this).getHikingActionPanel());
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        hikingPanel.add(actionPanel, layoutConstraints);

        JLabel userLabel = new JLabel("Users who completed the selected hiking");
        Font font = new Font("Courier", Font.BOLD,12);
        userLabel.setFont(font);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = defaultInsets;
        hikingPanel.add(userLabel, layoutConstraints);

        userTable = new JTable();
        userTable.setModel(new UserTableModel(new ArrayList<>()));
        userTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
        userTable.setFillsViewportHeight(true);

        JScrollPane userScrollPane = new JScrollPane(userTable);
        userScrollPane.setViewportView(userTable);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 4;
        hikingPanel.add(userScrollPane, layoutConstraints);

        refreshHikingList();

    }

    public JPanel getHikingPanel() {
        return hikingPanel;
    }

    public void refreshHikingList() {
        HikingService hikingService = new HikingServiceImpl();
        hikingList = hikingService.all();
        hikingTable.setModel(new HikingTableModel(hikingList));
    }

    public Hiking getSelectedHiking() {
        selectedHiking = selectedHikingPanel.getHikingData();
        return selectedHiking;
    }

    public void setSelectedHiking(Hiking hiking) {
        hikingTable.clearSelection();
        selectedHiking = Objects.requireNonNullElseGet(hiking, Hiking::new);
        selectedHikingPanel.handleSelectedHikingChange(selectedHiking);
    }

    public void refreshUserTable() {
        if (selectedHiking != null) {
            UserHikingService userHikingService = new UserHikingServiceImpl();
            List<User> userList = userHikingService.getUserListByHiking(selectedHiking.getId());
            userTable.setModel(new UserTableModel(userList));
        }
    }


}
