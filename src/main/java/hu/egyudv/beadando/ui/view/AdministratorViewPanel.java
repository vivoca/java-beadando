package hu.egyudv.beadando.ui.view;

import hu.egyudv.beadando.repository.HikingRepository;
import hu.egyudv.beadando.repository.HikingRepositoryFile;
import hu.egyudv.beadando.repository.UserRepository;
import hu.egyudv.beadando.repository.UserRepositoryFile;
import hu.egyudv.beadando.repository.entity.Hiking;
import hu.egyudv.beadando.repository.entity.User;
import hu.egyudv.beadando.service.*;
import hu.egyudv.beadando.ui.component.AdminActionPanel;
import hu.egyudv.beadando.ui.component.hiking.HikingTableModel;
import hu.egyudv.beadando.ui.component.hiking.SelectedHikingPanel;
import hu.egyudv.beadando.ui.component.user.SelectedUserPanel;
import hu.egyudv.beadando.ui.component.user.UserTableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdministratorViewPanel {

    private JPanel adminPanel;
    private JTable userTable;
    private JTable hikingTable;
    private JButton refreshBtn;

    private User selectedUser;
    private SelectedUserPanel selectedUserPanel;
    private List<User> userList = new ArrayList<>();
    private Hiking selectedHiking;
    private SelectedHikingPanel selectedHikingPanel;
    private List<Hiking> hikingList = new ArrayList<>();

    public AdministratorViewPanel() {
        Insets defaultInsets = new Insets(5, 25, 5, 25);

        adminPanel = new JPanel();
        adminPanel.setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();

        refreshBtn = createRefreshBtn();
        layoutConstraints.weightx = 0.5;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.insets = defaultInsets;
        layoutConstraints.anchor = GridBagConstraints.LINE_START;
        adminPanel.add(refreshBtn, layoutConstraints);

        layoutConstraints.anchor = GridBagConstraints.CENTER;


        JScrollPane userTablePanel = createUserTable();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 3;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        layoutConstraints.insets = defaultInsets;
        adminPanel.add(userTablePanel, layoutConstraints);

        JScrollPane hikingTablePanel = createHikingTable();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0;
        layoutConstraints.gridwidth = 3;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = defaultInsets;
        adminPanel.add(hikingTablePanel, layoutConstraints);

        JPanel userPanel = createSelectedUserPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.3;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = defaultInsets;
        adminPanel.add(userPanel, layoutConstraints);

        JPanel hikingPanel = createSelectedHikingPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.3;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = defaultInsets;
        adminPanel.add(hikingPanel, layoutConstraints);

        JPanel actionPanel = createActionPanel();
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.weightx = 0.3;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = defaultInsets;
        adminPanel.add(actionPanel, layoutConstraints);

        refreshTableData();
    }

    public JPanel getAdminPanel() {
        return adminPanel;
    }

    public void addUserHiking(String userId, String hikingId) {
        UserHikingService userHikingService = new UserHikingServiceImpl();
        userHikingService.save(userId, hikingId);
    }

    public void removeUserHiking(String userId, String hikingId) {
        UserHikingService userHikingService = new UserHikingServiceImpl();
        userHikingService.delete(userId, hikingId);
    }

    public void refreshTableData() {
        UserRepository userRepository = new UserRepositoryFile();
        UserService userService = new UserServiceImpl(userRepository);
        userList = userService.all();
        userTable.setModel(new UserTableModel(userList));

        HikingRepository hikingRepository = new HikingRepositoryFile();
        HikingService hikingService = new HikingServiceImpl(hikingRepository);
        hikingList = hikingService.all();
        hikingTable.setModel(new HikingTableModel(hikingList));
    }

    public void clear() {
        userTable.clearSelection();
        hikingTable.clearSelection();
        selectedUser = new User();
        selectedUserPanel.handleSelectedUserChange(selectedUser);
        selectedHiking = new Hiking();
        selectedHikingPanel.handleSelectedHikingChange(selectedHiking);
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public Hiking getSelectedHiking() {
        return selectedHiking;
    }

    private JButton createRefreshBtn() {
        refreshBtn = new JButton("Refresh Table Data");
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });
        return refreshBtn;
    }

    private JScrollPane createUserTable() {
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
            }
        });

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setViewportView(userTable);

        return scrollPane;
    }

    private JScrollPane createHikingTable() {
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
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane hikingScrollPane = new JScrollPane(hikingTable);
        hikingScrollPane.setViewportView(hikingTable);
        return hikingScrollPane;
    }

    private JPanel createSelectedUserPanel() {
        selectedUserPanel = new SelectedUserPanel(false);
        return selectedUserPanel.getSelectedUserPanel();
    }

    private JPanel createSelectedHikingPanel() {
        selectedHikingPanel = new SelectedHikingPanel(false);
        return selectedHikingPanel.getSelectedHikingPanel();
    }

    private JPanel createActionPanel() {
        return new AdminActionPanel(this).getAdminActionPanel();
    }

}
