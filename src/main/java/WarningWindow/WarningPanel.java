package WarningWindow;

import javax.swing.*;

public class WarningPanel {
    private JPanel MainPanel;
    public WarningPanel(){
        new JOptionPane("WARNING:If lagging, press the clear button a few times",JOptionPane.WARNING_MESSAGE,JOptionPane.OK_OPTION);
    }
}
