package hu.egyudv.beadando.ui.component;

import com.opencsv.bean.validators.MustMatchRegexExpression;
import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.HikingRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.service.HikingService;
import hu.egyudv.beadando.service.HikingServiceImpl;
import hu.egyudv.beadando.ui.view.HikingViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HikingActionPanel {

    private HikingViewPanel hikingViewPanel;
    private JPanel hikingActionPanel;

    public HikingActionPanel(HikingViewPanel hikingViewPanel) {
        this.hikingViewPanel = hikingViewPanel;
        hikingActionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        hikingActionPanel.setLayout(gridLayout);

        JButton saveBtn = new JButton("Save");
        hikingActionPanel.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hiking hiking = hikingViewPanel.getSelectedHiking();
                System.out.println("save: " + hiking);

                HikingRepository hikingRepository = new HikingRepositoryFile();
                HikingService hikingService = new HikingServiceImpl(hikingRepository);

                hikingService.save(hiking);
            }
        });

        JButton deleteBtn = new JButton("Delete");
        hikingActionPanel.add(deleteBtn);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });

        JButton usersBtn = new JButton("List Users");
        hikingActionPanel.add(usersBtn);
        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });

        JButton  clearBtn = new JButton("Clear");
        hikingActionPanel.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo
            }
        });
    }

    public JPanel getHikingActionPanel() {
        return hikingActionPanel;
    }
}
