package hu.egyudv.beadando.ui.component;


import javax.swing.*;

public class SignaturePanel extends BasePanel{

    private JPanel signaturePanel;

    public SignaturePanel() {
        signaturePanel = new JPanel();
        JLabel signatureLabel = new JLabel("Együd Viven - C11M1L (2021)");
        signaturePanel.add(signatureLabel);
    }

    public JPanel getSignaturePanel() {
        return signaturePanel;
    }
}
