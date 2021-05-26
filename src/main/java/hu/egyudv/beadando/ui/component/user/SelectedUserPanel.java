package hu.egyudv.beadando.ui.component.user;

import hu.egyudv.beadando.model.UserData;
import hu.egyudv.beadando.ui.component.BasePanel;

import javax.swing.*;
import java.awt.*;

public class SelectedUserPanel extends BasePanel {

    private JPanel selectedUserPanel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel mobileLabel;
    private JLabel birthDateLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField mobileTextField;
    private JTextField birthDateTextField;

    private UserData selectedUser;

    public SelectedUserPanel(boolean editable) {
        selectedUserPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(4,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        selectedUserPanel.setLayout(gridLayout);

        if (selectedUser == null) {
            selectedUser = new UserData();
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

        birthDateLabel = new JLabel ("Birth Date (yyyy.MM.dd)");
        selectedUserPanel.add(birthDateLabel);

        birthDateTextField = new JTextField();
        birthDateTextField.setEditable(editable);
        birthDateTextField.setToolTipText("yyyy.MM.dd");
        selectedUserPanel.add(birthDateTextField);

    }

    public JPanel getSelectedUserPanel() {
        return selectedUserPanel;
    }

    public void handleSelectedUserChange(UserData user) {
        selectedUser = user;
        firstNameTextField.setText(selectedUser.getFirstName());
        lastNameTextField.setText(selectedUser.getLastName());
        mobileTextField.setText(selectedUser.getMobile());
        birthDateTextField.setText(selectedUser.getFormattedBirthDate());
    }

    public UserData getUserData() {
        if (selectedUser == null) {
            selectedUser = new UserData();
        }
        boolean error = false;
        if (!firstNameTextField.getText().equals("")) {
            selectedUser.setFirstName(firstNameTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: First Name can't be empty!", MessageType.ERROR);
        }

        if (!lastNameTextField.getText().equals("")) {
            selectedUser.setLastName(lastNameTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: Last Name can't be empty!", MessageType.ERROR);
        }

        if (!mobileTextField.getText().equals("")) {
            selectedUser.setMobile(mobileTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: Mobile can't be empty!", MessageType.ERROR);
        }

        try {
            selectedUser.setFormattedBirthDate(birthDateTextField.getText());
        } catch (Exception ex) {
            error = true;
            System.out.println("Wrong Date format");
            showMsg("ERROR: Wrong Date format!\nRequired: yyyy.MM.dd", MessageType.ERROR);
        }

        if (error) {
            return null;
        }
        return selectedUser;
    }
}
