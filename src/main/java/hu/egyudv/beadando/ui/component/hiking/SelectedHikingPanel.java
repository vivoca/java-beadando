package hu.egyudv.beadando.ui.component.hiking;

import hu.egyudv.beadando.repository.entity.Difficulty;
import hu.egyudv.beadando.repository.entity.Hiking;
import org.apache.commons.lang3.builder.Diff;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SelectedHikingPanel {

    private JPanel selectedHikingPanel;
    private JLabel nameLabel;
    private JLabel lengthLabel;
    private JLabel locationLabel;
    private JLabel difficultyLabel;
    private JLabel descriptionLabel;
    private JTextField nameTextField;
    private JTextField lengthTextField;
    private JTextField locationTextField;
    private JLabel difficultyFieldLabel;
    private JSpinner difficultySpinner;
    private JTextField descriptionTextField;

    private Hiking selectedHiking;

    public SelectedHikingPanel(boolean editable) {
        selectedHikingPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(5,2);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        selectedHikingPanel.setLayout(gridLayout);

        if (selectedHiking == null) {
            selectedHiking = new Hiking();
        }

        nameLabel = new JLabel ("Name");
        selectedHikingPanel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setEditable(editable);
        selectedHikingPanel.add(nameTextField);

        lengthLabel = new JLabel ("Length");
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

        if (editable) {
            SpinnerListModel listModel = new SpinnerListModel(Difficulty.values());
            difficultySpinner = new JSpinner(listModel);
            difficultySpinner.setEditor(new JSpinner.DefaultEditor(difficultySpinner));
            selectedHikingPanel.add(difficultySpinner);
        } else {
            difficultyFieldLabel = new JLabel();
            selectedHikingPanel.add(difficultyFieldLabel);
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

    public void handleSelectedHikingChange(Hiking hiking) {
        selectedHiking = hiking;
        System.out.println("selectedhiking: " + selectedHiking);
        nameTextField.setText(selectedHiking.getName());
        lengthTextField.setText(Double.toString(selectedHiking.getLength()));
        locationTextField.setText(selectedHiking.getLocation());
        if (selectedHiking.getDifficulty() != null) {
            difficultySpinner.setValue(selectedHiking.getDifficulty());
//        difficultyFieldLabel.setText(selectedHiking.getDifficulty().label);
        }
        descriptionTextField.setText(selectedHiking.getDescription());
    }

    public Hiking getHikingData() {
        // todo validation
        selectedHiking.setName(nameTextField.getText());
        selectedHiking.setLength(Double.parseDouble(lengthTextField.getText()));
        selectedHiking.setLocation(locationTextField.getText());
        selectedHiking.setDifficulty((Difficulty) difficultySpinner.getValue());
//        selectedHiking.setDifficulty(Difficulty.getByLabel((String)difficultySpinner.getValue()));
        selectedHiking.setDescription(descriptionTextField.getText());
        return selectedHiking;
    }
}
