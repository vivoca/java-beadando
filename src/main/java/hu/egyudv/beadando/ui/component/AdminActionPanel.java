package hu.egyudv.beadando.ui.component;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.ui.view.AdministratorViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionPanel extends BasePanel{

    private JPanel adminActionPanel;
    private AdministratorViewPanel adminViewPanel;

    public AdminActionPanel(AdministratorViewPanel adminViewPanel) {
        this.adminViewPanel = adminViewPanel;

        adminActionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(3, 1);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        adminActionPanel.setLayout(gridLayout);

        JButton addBtn = new JButton("Add");
        adminActionPanel.add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserData user = adminViewPanel.getSelectedUser();
                HikingData hiking = adminViewPanel.getSelectedHiking();
                if (user != null && hiking != null) {
                    adminViewPanel.addUserHiking(user.getId(), hiking.getId());
                    showMsg("Add Done\nUser: " + user.getFirstName() + " " + user.getLastName() + "\nHiking: " + hiking.getName(), MessageType.INFO);
                }
            }
        });

        JButton removeBtn = new JButton("Remove");
        adminActionPanel.add(removeBtn);
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserData user = adminViewPanel.getSelectedUser();
                HikingData hiking = adminViewPanel.getSelectedHiking();
                if (user != null && hiking != null) {
                    adminViewPanel.removeUserHiking(user.getId(), hiking.getId());
                    showMsg("Remove Done\nUser: " + user.getFirstName() + " " + user.getLastName() + "\nHiking: " + hiking.getName(), MessageType.INFO);
                }
            }
        });

        JButton clearBtn = new JButton("Clear");
        adminActionPanel.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminViewPanel.clear();
            }
        });


    }

    public JPanel getAdminActionPanel() {
        return adminActionPanel;
    }
}
