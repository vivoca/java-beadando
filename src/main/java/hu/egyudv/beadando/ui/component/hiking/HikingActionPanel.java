package hu.egyudv.beadando.ui.component.hiking;

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
                hikingViewPanel.refreshHikingList();
            }
        });

        JButton deleteBtn = new JButton("Delete");
        hikingActionPanel.add(deleteBtn);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hiking hiking = hikingViewPanel.getSelectedHiking();
                if (hiking.getId() != null) {
                    System.out.println("delete: " + hiking);

                    HikingRepository hikingRepository = new HikingRepositoryFile();
                    HikingService hikingService = new HikingServiceImpl(hikingRepository);

                    hikingService.delete(hiking.getId());
                    hikingViewPanel.setSelectedHiking(null);
                    hikingViewPanel.refreshHikingList();
                } else {
                    System.out.println("ERROR - Hiking not selected");
                }
            }
        });

        JButton usersBtn = new JButton("List Users");
        hikingActionPanel.add(usersBtn);
        usersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hikingViewPanel.refreshUserTable();
            }
        });

        JButton  clearBtn = new JButton("Clear");
        hikingActionPanel.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hikingViewPanel.setSelectedHiking(null);
            }
        });
    }

    public JPanel getHikingActionPanel() {
        return hikingActionPanel;
    }
}
