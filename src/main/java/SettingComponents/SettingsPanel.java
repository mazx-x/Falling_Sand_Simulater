package SettingComponents;

import Particles.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsPanel extends JDialog {
    private JPanel MainPanel;
    private JSpinner spinnerFireLifeTime;
    private JSpinner spinnerSmokeLifeTime;
    private JLabel fireParticleLifeTimeLabel;
    private JLabel smokeParticleLifeTimeLabel;
    private SpinnerModel fireSpinnerModel;
    private SpinnerModel smokeSpinnerModel;

    public SettingsPanel(Component component){
        setMinimumSize(new Dimension(500,500));
        setModal(true);
        setTitle("Particle Settings");

        // Assuming createUIComponents is automatically called or a method you've defined
        // to initialize these components, we can update the models here.
        spinnerFireLifeTime = new JSpinner();
        spinnerSmokeLifeTime = new JSpinner();
        MainPanel = new JPanel();
        MainPanel.add(new JLabel("Fire Lifetime:"));
        MainPanel.add(spinnerFireLifeTime);
        MainPanel.add(new JLabel("Smoke Lifetime:"));
        MainPanel.add(spinnerSmokeLifeTime);

        // Initialize and set the models to the shared settings values
        SpinnerModel fireSpinnerModel = new SpinnerNumberModel(ParticleSettings.fireLifetime,
                1, 999999999, 1);
        spinnerFireLifeTime.setModel(fireSpinnerModel);

        SpinnerModel smokeSpinnerModel = new SpinnerNumberModel(ParticleSettings.smokeLifetime,
                1, 999999999, 1);
        spinnerSmokeLifeTime.setModel(smokeSpinnerModel);

        // Add a ChangeListener to the fire particle spinner
        spinnerFireLifeTime.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newFireLifeTime = (Integer) spinnerFireLifeTime.getValue();
                ParticleSettings.fireLifetime = newFireLifeTime; // Update the shared value
                System.out.println("Fire Particle Life Time changed to: " + newFireLifeTime);
            }
        });

        // Add a ChangeListener to the smoke particle spinner
        spinnerSmokeLifeTime.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int newSmokeLifeTime = (Integer) spinnerSmokeLifeTime.getValue();
                ParticleSettings.smokeLifetime = newSmokeLifeTime; // Update the shared value
                System.out.println("Smoke Particle Life Time changed to: " + newSmokeLifeTime);
            }
        });

        add(MainPanel);
        setLocationRelativeTo(component);
        pack();
        setVisible(true);
    }

    private void createUIComponents() {
        MainPanel = new JPanel();
//        fireSpinnerModel = new SpinnerNumberModel(FireParticle.DEFAULT_LIFE_TIME, 1,99999, 1);
//        smokeSpinnerModel = new SpinnerNumberModel(SmokeParticle.DEFAULT_LIFE_TIME, 1,99999, 1);
        spinnerFireLifeTime = new JSpinner();
        spinnerSmokeLifeTime = new JSpinner();
        fireParticleLifeTimeLabel = new JLabel();
        smokeParticleLifeTimeLabel = new JLabel();
    }
}

