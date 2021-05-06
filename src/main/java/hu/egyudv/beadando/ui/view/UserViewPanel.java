package hu.egyudv.beadando.ui;

import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.UserRepositoryFile;
import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.service.UserService;
import hu.egyudv.beadando.service.UserServiceImpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDialogPanel {
    private JPanel userPanel;
    private JTable userTable;
    private JTable hikingTable;

    private User selectedUser;
    private List<User> userList;
    private SelectedUserPanel selectedUserPanel;

    public UserDialogPanel() {
        Insets defaultInsets = new Insets(5,25,5,25);

        userPanel = new JPanel();
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        userPanel.setLayout(new GridBagLayout());

        refreshUserList();

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
                userTable.setModel(new UserTableModel(userList));
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
                selectedUser = userList.get(userTable.getSelectedRow()); // todo indexOutOfBounds refreshkor
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
        actionPanel.setSize(new Dimension(500, 200));


        selectedUserPanel = new SelectedUserPanel();
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

    }

    public JPanel getUserPanel() {
        return userPanel;
    }

    public void refreshUserList() {
        UserRepository userRepository = new UserRepositoryFile();
        UserService userService = new UserServiceImpl(userRepository);
        userList = userService.all();
    }

    protected User getSelectedUser() {
        selectedUser = selectedUserPanel.getUserData();
        return selectedUser;
    }

    protected void setSelectedUser(User user) {
        selectedUser = Objects.requireNonNullElseGet(user, User::new);
        selectedUserPanel.handleSelectedUserChange(selectedUser);
    }

    protected void refreshHikingTable() {
        System.out.println("refreshHikingTable - " + selectedUser);
        if (selectedUser != null) {
            hikingTable.setModel(new HikingTableModel(selectedUser.getCompletedHikingList()));
        }
    }

}
