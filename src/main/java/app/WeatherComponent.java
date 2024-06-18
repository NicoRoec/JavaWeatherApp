package app;

import javax.swing.*;

public abstract class WeatherComponent {
    protected JLabel component;

    public abstract void initialize();

    public JLabel getComponent() {
        return component;
    }
}
