package hu.egyudv.beadando.ui.component;

import hu.egyudv.beadando.repository.entity.User;

import javax.swing.*;
import java.awt.*;

public class SelectedUserPanel {

    private JPanel selectedUserPanel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel mobileLabel;
    private JLabel birthDateLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField mobileTextField;
    private JTextField birthDateTextField;

    private User selectedUser;

    public SelectedUserPanel(boolean editable) {
        selectedUserPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        selectedUserPanel.setLayout(gridLayout);

        if (selectedUser == null) {
            selectedUser = new User();
        }

        firstNameLabel = new JLabel ("First Name");
        selectedUserPanel.add(firstNameLabel);

        firstNameTextField = new JTextField();
        firstNameTextField.setEditable(editable);
        selectedUserPanel.add(firstNameTextField);

        lastNameLabel = new JLabel ("Last Name");
        selectedUserPanel.add(lastNameLabel);

        lastNameTextField = new JTextField();
        lastNameTextField.setEditable(editable);
        selectedUserPanel.add(lastNameTextField);

        mobileLabel = new JLabel ("Mobile");
        selectedUserPanel.add(mobileLabel);

        mobileTextField = new JTextField();
        mobileTextField.setEditable(editable);
        selectedUserPanel.add(mobileTextField);

        birthDateLabel = new JLabel ("Birth Date");
        selectedUserPanel.add(birthDateLabel);

        birthDateTextField = new JTextField();
        birthDateTextField.setEditable(editable);
        selectedUserPanel.add(birthDateTextField);

    }

    public JPanel getSelectedUserPanel() {
        return selectedUserPanel;
    }

    public void handleSelectedUserChange(User user) {
        selectedUser = user;
        firstNameTextField.setText(selectedUser.getFirstName());
        lastNameTextField.setText(selectedUser.getLastName());
        mobileTextField.setText(selectedUser.getMobile());
        birthDateTextField.setText(selectedUser.getFormattedBirthDate());
    }

    public User getUserData() {
        if (selectedUser == null) {
            selectedUser = new User();
        }
        selectedUser.setFirstName(firstNameTextField.getText());
        selectedUser.setLastName(lastNameTextField.getText());
        selectedUser.setMobile(mobileTextField.getText());
        try {
            selectedUser.setFormattedBirthDate(birthDateTextField.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return selectedUser;
    }
}
