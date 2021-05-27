package hu.egyudv.beadando.ui.view;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.ui.component.statistics.StatisticsActionPanel;
import hu.egyudv.beadando.ui.component.user.UserTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsViewPanel {
    private JPanel statisticsPanel;
    private JTable userTable;
    private JLabel sqlLabel;

    public StatisticsViewPanel() {
        Insets defaultInsets = new Insets(5,25,5,25);

        statisticsPanel = new JPanel();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        statisticsPanel.setLayout(new GridBagLayout());

        JPanel actionPanel = createActionPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = defaultInsets;
        statisticsPanel.add(actionPanel, layoutConstraints);

        sqlLabel = new JLabel("");
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = defaultInsets;
        statisticsPanel.add(sqlLabel, layoutConstraints);

        JScrollPane userTablePanel = createUserTable();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 2;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        statisticsPanel.add(userTablePanel, layoutConstraints);

    }

    public JPanel getStatisticsPanel() {
        return statisticsPanel;
    }

    public void refreshTableData(List<UserData> userList) {
        userTable.setModel(new UserTableModel(userList));
    }

    public void setSqlLabel(String sql) {
        sqlLabel.setText("Last executed sql query: " + sql);
    }

    private JScrollPane createUserTable() {
        userTable = new JTable();
        userTable.setModel(new UserTableModel(new ArrayList<>()));
        userTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
        userTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setViewportView(userTable);

        return scrollPane;
    }

    private JPanel createActionPanel() {
        return new StatisticsActionPanel(this).getStatsActionPanel();
    }
}
