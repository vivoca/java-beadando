package hu.egyudv.beadando.ui.component.hiking;

import hu.egyudv.beadando.model.HikingData;
import hu.egyudv.beadando.model.Difficulty;
import hu.egyudv.beadando.ui.component.BasePanel;

import javax.swing.*;
import java.awt.*;

public class SelectedHikingPanel extends BasePanel {

    private JPanel selectedHikingPanel;
    private JLabel nameLabel;
    private JLabel lengthLabel;
    private JLabel locationLabel;
    private JLabel difficultyLabel;
    private JLabel descriptionLabel;
    private JTextField nameTextField;
    private JTextField lengthTextField;
    private JTextField locationTextField;
    private JTextField difficultyTextField;
    private JSpinner difficultySpinner;
    private JTextField descriptionTextField;

    private HikingData selectedHiking;

    public SelectedHikingPanel(boolean editable) {
        selectedHikingPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(5,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        selectedHikingPanel.setLayout(gridLayout);

        if (selectedHiking == null) {
            selectedHiking = new HikingData();
        }

        nameLabel = new JLabel ("Name");
        selectedHikingPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setEditable(editable);
        selectedHikingPanel.add(nameTextField);

        lengthLabel = new JLabel ("Length (n.n)");
        selectedHikingPanel.add(lengthLabel);

        lengthTextField = new JTextField();
        lengthTextField.setEditable(editable);
        selectedHikingPanel.add(lengthTextField);

        locationLabel = new JLabel ("Location");
        selectedHikingPanel.add(locationLabel);

        locationTextField = new JTextField();
        locationTextField.setEditable(editable);
        selectedHikingPanel.add(locationTextField);

        difficultyLabel = new JLabel ("Difficulty");
        selectedHikingPanel.add(difficultyLabel);

        SpinnerListModel listModel = new SpinnerListModel(Difficulty.values());
        difficultySpinner = new JSpinner(listModel);
        difficultySpinner.setEditor(new JSpinner.DefaultEditor(difficultySpinner));
        difficultyTextField = new JTextField();
        difficultyTextField.setEditable(false);
        if (editable) {
            selectedHikingPanel.add(difficultySpinner);
        } else {
            selectedHikingPanel.add(difficultyTextField);
        }

        descriptionLabel = new JLabel ("Description");
        selectedHikingPanel.add(descriptionLabel);

        descriptionTextField = new JTextField();
        descriptionTextField.setEditable(editable);
        selectedHikingPanel.add(descriptionTextField);

    }

    public JPanel getSelectedHikingPanel() {
        return selectedHikingPanel;
    }

    public void handleSelectedHikingChange(HikingData hiking) {
        selectedHiking = hiking;
        nameTextField.setText(selectedHiking.getName());
        lengthTextField.setText(Double.toString(selectedHiking.getLength()));
        locationTextField.setText(selectedHiking.getLocation());
        if (selectedHiking.getDifficulty() != null) {
            difficultySpinner.setValue(selectedHiking.getDifficulty());
            difficultyTextField.setText(selectedHiking.getDifficulty().label);
        } else {
            difficultyTextField.setText("");
        }
        descriptionTextField.setText(selectedHiking.getDescription());
    }

    public HikingData getHikingData() {
        boolean error = false;
        if (!nameTextField.getText().equals("")) {
            selectedHiking.setName(nameTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: Name can't be empty!", MessageType.ERROR);
        }

        try {
            selectedHiking.setLength(Double.parseDouble(lengthTextField.getText()));
        } catch (Exception ex) {
            error = true;
            showMsg("ERROR: Wrong length format!\nRequired form: n.n", MessageType.ERROR);
        }

        if (!locationTextField.getText().equals("")) {
            selectedHiking.setLocation(locationTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: Location can't be empty!", MessageType.ERROR);
        }

        selectedHiking.setDifficulty((Difficulty) difficultySpinner.getValue());

        if (!descriptionTextField.getText().equals("")) {
            selectedHiking.setDescription(descriptionTextField.getText());
        } else {
            error = true;
            showMsg("ERROR: Description can't be empty!", MessageType.ERROR);
        }

        if (error) {
            return null;
        }
        return selectedHiking;
    }
}
