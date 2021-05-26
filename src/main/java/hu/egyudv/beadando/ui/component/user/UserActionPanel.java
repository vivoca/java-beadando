package hu.egyudv.beadando.ui.component.user;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.service.UserService;
import hu.egyudv.beadando.service.UserServiceImpl;
import hu.egyudv.beadando.ui.component.BasePanel;
import hu.egyudv.beadando.ui.view.UserViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActionPanel extends BasePanel {

    private UserViewPanel userViewPanel;
    private JPanel userActionPanel;

    private final UserService userService = new UserServiceImpl();

    public UserActionPanel(UserViewPanel userViewPanel) {
        this.userViewPanel = userViewPanel;
        userActionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        userActionPanel.setLayout(gridLayout);

        JButton saveBtn = new JButton("Save");
        userActionPanel.add(saveBtn);
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserData user = userViewPanel.getSelectedUser();
                if (user != null) {
                System.out.println("save: " + user);

                userService.save(user);
                userViewPanel.refreshUserList();
                showMsg("Save Done\nUser: " + user.getFirstName() + " " + user.getLastName(), MessageType.INFO);
                } else {
                    System.out.println("ERROR - User not selected");
                    showMsg("Save ERROR\nUser not selected ", MessageType.ERROR);
                }

            }
        });

        JButton deleteBtn = new JButton("Delete");
        userActionPanel.add(deleteBtn);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UserData user = userViewPanel.getSelectedUser();
                if (user != null) {
                    System.out.println("delete: " + user);

                    userService.delete(user.getId());
                    userViewPanel.setSelectedUser(null);
                    userViewPanel.refreshUserList();
                    showMsg("Delete Done\nUser: " + user.getFirstName() + " " + user.getLastName(), MessageType.INFO);
                } else {
                    System.out.println("ERROR - User not selected");
                    showMsg("Delete ERROR\nUser not selected ", MessageType.ERROR);
                }
            }
        });

        JButton hikingsBtn = new JButton("List Hikings");
        userActionPanel.add(hikingsBtn);
        hikingsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userViewPanel.refreshHikingTable();
            }
        });

        JButton clearBtn = new JButton("Clear");
        userActionPanel.add(clearBtn);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userViewPanel.setSelectedUser(null);
            }
        });
    }

    public JPanel getUserActionPanel() {
        return userActionPanel;
    }
}
