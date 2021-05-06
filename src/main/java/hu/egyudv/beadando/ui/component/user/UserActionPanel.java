package hu.egyudv.beadando.ui.component;

import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.UserRepositoryFile;
import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.service.UserService;
import hu.egyudv.beadando.service.UserServiceImpl;
import hu.egyudv.beadando.ui.view.UserViewPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserActionPanel {

    private UserViewPanel userViewPanel;
    private JPanel userActionPanel;

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

                User user = userViewPanel.getSelectedUser();
                System.out.println("save: " + user);

                UserRepository userRepository = new UserRepositoryFile();
                UserService userService = new UserServiceImpl(userRepository);

                userService.save(user);

            }
        });

        JButton deleteBtn = new JButton("Delete");
        userActionPanel.add(deleteBtn);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                User user = userViewPanel.getSelectedUser();
                if (user.getId() != null) {
                    System.out.println("delete: " + user);

                    UserRepository userRepository = new UserRepositoryFile();
                    UserService userService = new UserServiceImpl(userRepository);

                    userService.delete(user.getId());
                } else {
                    System.out.println("ERROR - User not selected");
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
