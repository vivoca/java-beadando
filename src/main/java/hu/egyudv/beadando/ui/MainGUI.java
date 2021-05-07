package hu.egyudv.beadando.ui;

import hu.egyudv.beadando.ui.view.AdministratorViewPanel;
import hu.egyudv.beadando.ui.view.HikingViewPanel;
import hu.egyudv.beadando.ui.view.UserViewPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGUI implements ItemListener {
    JPanel cards;
    final static String ADMINISTRATOR_MENU = "Administrator Menu";
    final static String USER_MENU = "User Menu";
    final static String HIKING_MENU = "Hiking Menu";
    final static String STATISTICS_MENU = "Statistics Menu";
    final static String WEATHER_FORECAST_MENU = "Weather Forecast";

    public void addComponentToPane(Container pane) {
        JPanel comboBoxPane = new JPanel();
        JLabel menuChooseLabel = new JLabel();
        menuChooseLabel.setText("Choose a menu: ");
        comboBoxPane.add(menuChooseLabel);
        String comboBoxItems[] = {ADMINISTRATOR_MENU, USER_MENU, HIKING_MENU, STATISTICS_MENU, WEATHER_FORECAST_MENU};
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);

        JPanel card0 = new JPanel();
        card0.add(new JLabel("Administration Coming Soon..."));

        JPanel card3 = new JPanel();
        card3.add(new JLabel("Statistics Coming Soon..."));

        JPanel card4 = new JPanel();
        card4.add(new JLabel("Weather Forecast Coming Soon..."));

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(new AdministratorViewPanel().getAdminPanel(), ADMINISTRATOR_MENU);
        cards.add(new UserViewPanel().getUserPanel(), USER_MENU);
        cards.add(new HikingViewPanel().getHikingPanel(), HIKING_MENU);
        cards.add(card3, STATISTICS_MENU);
        cards.add(card4, WEATHER_FORECAST_MENU);

        pane.add(comboBoxPane, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Hiking Administration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        MainGUI demo = new MainGUI();
        demo.addComponentToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(650,650);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
