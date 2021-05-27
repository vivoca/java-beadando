package hu.egyudv.beadando.ui.component.statistics;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.service.StatisticsService;
import hu.egyudv.beadando.service.StatisticsServiceImpl;
import hu.egyudv.beadando.ui.view.StatisticsViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StatisticsActionPanel {

    private JPanel statsActionPanel;
    private StatisticsViewPanel statViewPanel;

    private final StatisticsService statService = new StatisticsServiceImpl();

    public StatisticsActionPanel(StatisticsViewPanel statViewPanel) {
        this.statViewPanel = statViewPanel;

        statsActionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 1);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        statsActionPanel.setLayout(gridLayout);

        JButton stat1Btn = new JButton("Users completed more than 5 hiking");
        statsActionPanel.add(stat1Btn);
        stat1Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserData> userList = statService.statMoreThan5Hiking();
                statViewPanel.setSqlLabel("SELECT distinct _uh.user FROM UserHiking _uh WHERE _uh.user IN \n" +
                        "(SELECT _uh2.user FROM UserHiking _uh2 GROUP BY user HAVING COUNT(*) >= 5)");
                statViewPanel.refreshTableData(userList);
            }
        });

        JButton stat2Btn = new JButton("Users age between 15 and 20");
        statsActionPanel.add(stat2Btn);
        stat2Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserData> userList = statService.statAgeBetween15And20();
                statViewPanel.setSqlLabel("select _user from User _user where _user.birthDate >= '2001.01.01' and _user.birthDate <= '2006.12.31'");
                statViewPanel.refreshTableData(userList);
            }
        });

        JButton stat3Btn = new JButton("Users born in July");
        statsActionPanel.add(stat3Btn);
        stat3Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserData> userList = statService.statBornInJuly();
                statViewPanel.setSqlLabel("select _user from User _user where to_char(_user.birthDate, 'MM') = '07'");
                statViewPanel.refreshTableData(userList);
            }
        });

        JButton stat4Btn = new JButton("Users completed MEDIUM hiking");
        statsActionPanel.add(stat4Btn);
        stat4Btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<UserData> userList = statService.statCompletedMediumHike();
                statViewPanel.setSqlLabel("select distinct _uh.user from UserHiking _uh where _uh.hiking.difficulty = 'MEDIUM'");
                statViewPanel.refreshTableData(userList);
            }
        });


    }

    public JPanel getStatsActionPanel() {
        return statsActionPanel;
    }

}
