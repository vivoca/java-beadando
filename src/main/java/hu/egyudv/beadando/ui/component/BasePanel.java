package hu.egyudv.beadando.ui.component;

import javax.swing.*;

public class BasePanel {

    public void showMsg(String message, MessageType type) {
        JOptionPane.showMessageDialog(null, message, "Message Dialog", type.value);
    }

    public enum MessageType {
        STOP(0),
        INFO(1),
        ERROR(2),
        QUESTION(3);

        public final int value;

        MessageType(int value) {
            this.value = value;
        }
    }
}
