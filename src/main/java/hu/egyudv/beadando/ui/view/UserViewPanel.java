package hu.egyudv.beadando.ui.view;

import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.UserRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.service.UserHikingService;
import hu.egyudv.beadando.service.UserHikingServiceImpl;
import hu.egyudv.beadando.service.UserService;
import hu.egyudv.beadando.service.UserServiceImpl;
import hu.egyudv.beadando.ui.component.hiking.HikingTableModel;
import hu.egyudv.beadando.ui.component.user.SelectedUserPanel;
import hu.egyudv.beadando.ui.component.user.UserActionPanel;
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

public class UserViewPanel {
    private JPanel userPanel;
    private JTable userTable;
    private JTable hikingTable;

    private User selectedUser;
    private List<User> userList  = new ArrayList<>();
    private SelectedUserPanel selectedUserPanel;

    public UserViewPanel() {
        Insets defaultInsets = new Insets(5,25,5,25);

        userPanel = new JPanel();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        userPanel.setLayout(new GridBagLayout());

        JButton refreshBtn = new JButton("Refresh User Table");
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = defaultInsets;
        layoutConstraints.anchor = GridBagConstraints.LINE_START;
        userPanel.add(refreshBtn, layoutConstraints);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshUserList();
            }
        });
        //default
        layoutConstraints.anchor = GridBagConstraints.CENTER;


        userTable = new JTable();
        userTable.setModel(new UserTableModel(userList));
        userTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
        userTable.setFillsViewportHeight(true);
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                int rowNum = userTable.getSelectedRow();
                if (rowNum >= 0) {
                    selectedUser = userList.get(userTable.getSelectedRow());
                }
                selectedUserPanel.handleSelectedUserChange(selectedUser);
                hikingTable.setModel(new HikingTableModel(new ArrayList<>()));
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setViewportView(userTable);


        //Add the scroll pane to this panel.
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = defaultInsets;
        userPanel.add(scrollPane, layoutConstraints);


        JPanel actionPanel = new JPanel();
        GridLayout actionGridLayout = new GridLayout(0, 2);
        actionGridLayout.setHgap(5);
        actionGridLayout.setVgap(5);
        actionPanel.setLayout(actionGridLayout);


        selectedUserPanel = new SelectedUserPanel(true);
        actionPanel.add(selectedUserPanel.getSelectedUserPanel());
        actionPanel.add(new UserActionPanel(this).getUserActionPanel());
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        userPanel.add(actionPanel, layoutConstraints);


        JLabel hikingLabel = new JLabel("User's completed hikings");
        Font font = new Font("Courier", Font.BOLD,12);
        hikingLabel.setFont(font);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = defaultInsets;
        userPanel.add(hikingLabel, layoutConstraints);


        hikingTable = new JTable();
        hikingTable.setModel(new HikingTableModel(new ArrayList<>()));
        hikingTable.setPreferredScrollableViewportSize(new Dimension(500, 120));
        hikingTable.setFillsViewportHeight(true);


        JScrollPane hikingScrollPane = new JScrollPane(hikingTable);
        hikingScrollPane.setViewportView(hikingTable);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 4;
        userPanel.add(hikingScrollPane, layoutConstraints);

        refreshUserList();

    }

    public JPanel getUserPanel() {
        return userPanel;
    }

    public void refreshUserList() {
        UserRepository userRepository = new UserRepositoryFile();
        UserService userService = new UserServiceImpl(userRepository);
        userList = userService.all();
        userTable.setModel(new UserTableModel(userList));
    }

    public User getSelectedUser() {
        selectedUser = selectedUserPanel.getUserData();
        return selectedUser;
    }

    public void setSelectedUser(User user) {
        userTable.clearSelection();
        selectedUser = Objects.requireNonNullElseGet(user, User::new);
        selectedUserPanel.handleSelectedUserChange(selectedUser);
    }

    public void refreshHikingTable() {
        if (selectedUser != null) {
            UserHikingService userHikingService = new UserHikingServiceImpl();
            List<Hiking> hikingList = userHikingService.getHikingListByUser(selectedUser.getId());
            hikingTable.setModel(new HikingTableModel(hikingList));
        }
    }

}
